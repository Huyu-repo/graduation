package cn.xsshome.mvcdo.vo;

import java.util.List;

/**
 * @author 小帅丶
 * @date 2018年6月4日
 * <p>Description: 手势识别返回页面的对象</p>
 */
public class YouTuFuseResponse extends RestResponse{
	private String label;
	private String confidence;
	private int classifyCnt;
	private String age;
	private String gender;
	//速算结果集
	private List<QuickCalResult> results;
	//眼镜[0不戴眼镜 1戴眼镜 2戴墨镜] 
	private String glasses;
	//微笑[0(normal)~50(smile)~100(laugh)]
	private String expression;
	//魅力[0~100]
	private String beauty;
	
	public List<QuickCalResult> getResults() {
		return results;
	}
	public void setResults(List<QuickCalResult> results) {
		this.results = results;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGlasses() {
		return glasses;
	}
	public void setGlasses(String glasses) {
		this.glasses = glasses;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getBeauty() {
		return beauty;
	}
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}
	public int getClassifyCnt() {
		return classifyCnt;
	}
	public void setClassifyCnt(int classifyCnt) {
		this.classifyCnt = classifyCnt;
	}
	public static class QuickCalResult{
		private String itemString;
		private String item;
		public String getItemString() {
			return itemString;
		}
		public void setItemString(String itemString) {
			this.itemString = itemString;
		}
		public String getItem() {
			return item;
		}
		public void setItem(String item) {
			this.item = item;
		}
	}
}
