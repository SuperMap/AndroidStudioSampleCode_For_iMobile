package com.supermap.imobile.control;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.supermap.imobile.adapter.ServicesAdapter;
import com.supermap.imobile.bean.ServicesBean;
import com.supermap.iportalservices.IPortalService;
import com.supermap.iportalservices.OnResponseListener;
import com.supermap.imobile.iportalservices.MainActivity;
import com.yalantis.phoenix.PullToRefreshView;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 服务资源
 */
public class ServicesControl {
    private static final String TAG = "MyServicesControl";

    private MainActivity mMainActivity = null;
    private ListView mListView = null;
    private PullToRefreshView mPullToRefreshView;
    private ServicesAdapter adapter = null;

    public ServicesControl(Context context, ListView listView, PullToRefreshView pullToRefreshView) {
        mMainActivity = (MainActivity) context;
        mListView = listView;
        mPullToRefreshView = pullToRefreshView;
        mPullToRefreshView.setOnRefreshListener(refreshListener);
    }

    /**
     * 联网请求更新数据
     */
    public void getServices() {
        try {

            HashMap<String, String> searchParameter = new HashMap<>();
            searchParameter.put("pageSize", "20");
            IPortalService.getInstance().getServices(searchParameter,listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下拉刷新
     */
    PullToRefreshView.OnRefreshListener refreshListener = new PullToRefreshView.OnRefreshListener() {
        @Override
        public void onRefresh() {
            try {

                HashMap<String, String> searchParameter = new HashMap<>();
                searchParameter.put("pageSize", "20");
                IPortalService.getInstance().getServices(searchParameter,listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 联网回调
     */
    private OnResponseListener listener = new OnResponseListener() {
        @Override
        public void onError(final Exception exception) {
            if (exception != null) {
                Log.e("onFailed", exception.getMessage());
            }

            mMainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mMainActivity,"刷新失败：" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    mPullToRefreshView.setRefreshing(false);
                }
            });
        }

        @Override
        public void onComplete(final Response response) {
            String responseBody = null;
            ServicesBean servicesBean = null;
            try {
                responseBody = response.body().string();

                JSONObject root = new JSONObject(responseBody);
                if (root.has("error")) {
                    JSONObject error = new JSONObject(root.getString("error"));
                    final String errorMsg = error.getString("errorMsg");
                    final String code = error.getString("code");
                    showToast(code + "：" + errorMsg);
                    setRefresh(false);
                    return;
                } else {
                    Gson gson = new Gson();
                    servicesBean = gson.fromJson(responseBody, ServicesBean.class);
                    if (servicesBean == null) {
                        showToast("刷新失败");
                        setRefresh(false);
                        return;
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
                showToast(e.getMessage());
                return;
            }
            if (adapter == null) {
                adapter = new ServicesAdapter(mMainActivity, servicesBean);
            } else {
                adapter.setData(servicesBean);
            }
            mMainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mListView.setAdapter(adapter);
                    mPullToRefreshView.setRefreshing(false);
                    Toast.makeText(mMainActivity, "刷新成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private void showToast(final String msg){
        if (mMainActivity != null) {
            mMainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mMainActivity, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setRefresh(final boolean refresh){
        if (mMainActivity != null) {
            mMainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPullToRefreshView.setRefreshing(refresh);
                }
            });
        }
    }
}
