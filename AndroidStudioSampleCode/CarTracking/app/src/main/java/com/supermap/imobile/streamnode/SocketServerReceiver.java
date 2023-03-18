package com.supermap.imobile.streamnode;

import java.util.List;

public class SocketServerReceiver extends StreamNode {

    public SocketServerReceiver() {
        this.className = "com.supermap.bdt.streaming.receiver.SocketServerReceiver";
    }

    /**
     * prevNodes : []
     * description : null
     * reader : {"className":"com.supermap.bdt.streaming.formatter.CSVFormatter","separator":",","isJsonArray":"false","arrayExpression":"arrayexpression"}
     * metadata : {"epsg":2678,"idFieldName":"idname","dateTimeFormat":"yyyy-MM-dd","featureType":"TABULAR","fieldInfos":[{"name":"字段名称","source":"字段来源","nType":"TEXT"}]}
     * port : 8080
     */

    private ReaderBean reader;
    private MetadataBean metadata;
    private int port;

    public ReaderBean getReader() {
        return reader;
    }

    public void setReader(ReaderBean reader) {
        this.reader = reader;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static class ReaderBean {
        /**
         * className : com.supermap.bdt.streaming.formatter.CSVFormatter
         * separator : ,
         * isJsonArray : false
         * arrayExpression : arrayexpression
         */

        private String className;
        private String separator;
        private String isJsonArray;
        private String arrayExpression;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSeparator() {
            return separator;
        }

        public void setSeparator(String separator) {
            this.separator = separator;
        }

        public String getIsJsonArray() {
            return isJsonArray;
        }

        public void setIsJsonArray(String isJsonArray) {
            this.isJsonArray = isJsonArray;
        }

        public String getArrayExpression() {
            return arrayExpression;
        }

        public void setArrayExpression(String arrayExpression) {
            this.arrayExpression = arrayExpression;
        }
    }

    public static class MetadataBean {
        /**
         * epsg : 2678
         * idFieldName : idname
         * dateTimeFormat : yyyy-MM-dd
         * featureType : TABULAR
         * fieldInfos : [{"name":"字段名称","source":"字段来源","nType":"TEXT"}]
         */

        private int epsg;
        private String idFieldName;
        private String dateTimeFormat;
        private String featureType;
        private List<FieldInfosBean> fieldInfos;

        public int getEpsg() {
            return epsg;
        }

        public void setEpsg(int epsg) {
            this.epsg = epsg;
        }

        public String getIdFieldName() {
            return idFieldName;
        }

        public void setIdFieldName(String idFieldName) {
            this.idFieldName = idFieldName;
        }

        public String getDateTimeFormat() {
            return dateTimeFormat;
        }

        public void setDateTimeFormat(String dateTimeFormat) {
            this.dateTimeFormat = dateTimeFormat;
        }

        public String getFeatureType() {
            return featureType;
        }

        public void setFeatureType(String featureType) {
            this.featureType = featureType;
        }

        public List<FieldInfosBean> getFieldInfos() {
            return fieldInfos;
        }

        public void setFieldInfos(List<FieldInfosBean> fieldInfos) {
            this.fieldInfos = fieldInfos;
        }

        public static class FieldInfosBean {
            /**
             * name : 字段名称
             * source : 字段来源
             * nType : TEXT
             */

            private String name;
            private String source;
            private String nType;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getNType() {
                return nType;
            }

            public void setNType(String nType) {
                this.nType = nType;
            }
        }
    }
}
