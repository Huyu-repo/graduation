package cn.xsshome.mvcdo.service.ai.tencent;

import java.util.List;
import java.util.Map;

import cn.xsshome.mvcdo.pojo.ai.tencent.dbo.YouTuDetectFuseDO;

/**
 *
 * @author 糊鱼
 * @date 20120年3月18日
 * <p>Description: 优图接口类</p>
 */
public interface YouTuFuseService {
	List<YouTuDetectFuseDO> listYouTuFuse(Map<String, Object> map);
	int countYouTuFuse(Map<String,Object> map);
	int saveYouTuFuse(YouTuDetectFuseDO youTuDetectFuseDO);
	int removeYouTuFuse(Long youtuId);
	int batchRemoveYouTuFuse(Long[] youtuIds);
}
