package com.supermap.imobile.streamingservice;

import okhttp3.Response;

/**
 * ����ص��ӿ�
 * @author Tron
 *
 */
public interface OnResponseListener {

	void onFailed(Exception exception);

	void onResponse(Response response);

}
