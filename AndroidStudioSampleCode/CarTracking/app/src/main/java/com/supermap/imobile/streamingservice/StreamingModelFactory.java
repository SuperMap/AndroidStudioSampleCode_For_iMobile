package com.supermap.imobile.streamingservice;

import com.google.gson.Gson;
import com.supermap.imobile.streamnode.StreamNode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ������ģ��
 *
 * //    SingleTextFileReceiver: "���ı��ļ�������",
 * //    SocketReceiver: "Socket�ͻ��˽�����",
 * //    MultiSocketReceiver: "Socket��ͻ��˽�����",
 * //    SocketServerReceiver: "Socket���������",
 * //    WebSocketReceiver: "WebSocket������",
 * //    TextFileReceiver: "�ı��ļ�������",
 * //    KafkaReceiver: "Kafka������",
 * //    HttpReceiver: "Http������",
 * //    JMSReceiver: "JMS������",
 * //
 * //    WebSocketClientSender: "WebSocket������",
 * //    FileSender: "�ļ�������",
 * //    JMSSender: "JMS��Ϣ������",
 * //    SMSSender: "������Ϣ������",
 * //    SocketClientSender: "Socket�ͻ��˷�����",
 * //    SocketServerSender: "Socket����˷�����",
 * //    EsAppendSender: "Elasticsearch��ӷ�����",
 * //    EsUpdateSender: "Elasticsearch���·�����",
 * //
 * //    FeatureInsertMapper: "�ֶ����ת����",
 * //    StaticRDDJoinMapper: "��̬��Դ��չ",
 * //    FeatureDeleteMapper: "�ֶ�ɾ��ת����",
 * //    FeatureMapMapper: "�ֶ�ӳ��ת����",
 * //    FeatureCalculateMapper: "�ֶ�����ת����",
 * //    GeoTaggerMapper: "����Χ��ת����",
 * //
 * //    FeatureFilter: "�߼����������",
 * //    GeoFilter: "���������"
 */
public class StreamingModelFactory {
    private static final String TAG = "StreamingModelFactory";

    private String checkPointDir = "tmp"; //���� Streaming ��CheckPoint���ܵı���Ŀ¼��String ���͡�
    private int interval = 5000; // ���� Streaming ���еļ��ʱ�䣬��λΪ���롣int ���͡�
    private int version = 9000;

    private static StreamingModelFactory mStreamingModelFactory = null;

    public static StreamingModelFactory getInstance() {
        if (mStreamingModelFactory == null) {
            mStreamingModelFactory = new StreamingModelFactory();
        }
        return mStreamingModelFactory;
    }

    public String getCheckPointDir() {
        return checkPointDir;
    }

    public void setCheckPointDir(String checkPointDir) {
        this.checkPointDir = checkPointDir;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public JSONObject buildNodeDic(StreamNode streamNode) {
        try {
            Gson gson = new Gson();
            String geoFilterJson = gson.toJson(streamNode);
            return new JSONObject(geoFilterJson);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ����������ģ��
     * @return
     */
    public JSONObject createStreamingModel(JSONObject nodeDic) {
        try {
            JSONObject root = new JSONObject();
            JSONObject sparkParameter = new JSONObject();
            JSONObject stream = new JSONObject();

            sparkParameter.put("checkPointDir", checkPointDir);
            sparkParameter.put("interval", interval);

            stream.put("nodeDic",nodeDic);

            root.put("sparkParameter", sparkParameter);
            root.put("stream", stream);
            root.put("version", version);

            return root;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
