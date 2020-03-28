package cn.xsshome.mvcdo.healthai.response;


import java.util.Date;
import java.util.List;

/**
 * @Description 健康有益面相识别对象
 * @author 小帅丶
 * @className Physiognomy
 * @Date 2019/8/22-16:00
 **/
public class Physiognomy {
    private ResData resData;

    public ResData getResData() {
        return resData;
    }

    public void setResData(ResData resData) {
        this.resData = resData;
    }

    public static class ResData{
        private String retCode;//处理码
        private String retInfo;//处理信息
        private Date curTime;//当前系统时间
        private String sn;//请求编号
        private RecognitionResult recognitionResult;//识别结果

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getRetInfo() {
            return retInfo;
        }

        public void setRetInfo(String retInfo) {
            this.retInfo = retInfo;
        }

        public Date getCurTime() {
            return curTime;
        }

        public void setCurTime(Date curTime) {
            this.curTime = curTime;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public RecognitionResult getRecognitionResult() {
            return recognitionResult;
        }

        public void setRecognitionResult(RecognitionResult recognitionResult) {
            this.recognitionResult = recognitionResult;
        }
    }
    public static class RecognitionResult {
        private int imageWidth;//图片宽度
        private int imageHeight;//图片高度
        private int status;//识别结果状态
        private int right;//面部区域右边界
        private int left;//面部区域左边界
        private int top;//面部区域上边界
        private int bottom;//面部区域下边界
        private Brow brow;//眉形数据
        private Eye eye;//眼型数据
        private Nose nose;//鼻型数据
        private Mouth mouth;//嘴型数据
        private Face face;//脸型数据

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public Brow getBrow() {
            return brow;
        }

        public void setBrow(Brow brow) {
            this.brow = brow;
        }

        public Eye getEye() {
            return eye;
        }

        public void setEye(Eye eye) {
            this.eye = eye;
        }

        public Nose getNose() {
            return nose;
        }

        public void setNose(Nose nose) {
            this.nose = nose;
        }

        public Mouth getMouth() {
            return mouth;
        }

        public void setMouth(Mouth mouth) {
            this.mouth = mouth;
        }

        public Face getFace() {
            return face;
        }

        public void setFace(Face face) {
            this.face = face;
        }
    }
    public static class Brow {
        private String shape;//形状
        private List<String> paraphrase;//概述

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<String> getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(List<String> paraphrase) {
            this.paraphrase = paraphrase;
        }
    }
    public static class Eye {
        private String shape;
        private List<String> paraphrase;

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<String> getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(List<String> paraphrase) {
            this.paraphrase = paraphrase;
        }
    }
    public static class Nose {
        private String shape;
        private List<String> paraphrase;

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<String> getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(List<String> paraphrase) {
            this.paraphrase = paraphrase;
        }
    }
    public static class Mouth {
        private String shape;
        private List<String> paraphrase;

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<String> getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(List<String> paraphrase) {
            this.paraphrase = paraphrase;
        }
    }
    public static class Face {
        private String shape;
        private List<String> paraphrase;

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public List<String> getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(List<String> paraphrase) {
            this.paraphrase = paraphrase;
        }
    }
}
