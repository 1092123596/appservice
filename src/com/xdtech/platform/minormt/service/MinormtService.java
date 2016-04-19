package com.xdtech.platform.minormt.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xdtech.platform.core.service.BaseService;
import com.xdtech.platform.minormt.bean.MtMinormt;

@Service
public class MinormtService extends BaseService{
	
	/**
	 * 查询所有做小保养的商户
	 * @author shendelei
	 * @date 2015-2-28 下午4:13:24
	 * @return
	 */
	public List<HashMap> findMinormtShopList(int start, int row, String shopName, String lng, String lat, int deptId){
//		lng1 = "116.424321";
//		lat1 = "39.895155";
		if(StringUtils.isEmpty(lng)){
			lng = "0";
		}
		if(StringUtils.isEmpty(lat)){
			lat = "0";
		}
		StringBuffer sql = new StringBuffer("select mt_minormt.dmprice,mt_minormt.webprice,gc_shop.shopName,gc_shop.addr,gc_shop.shopId,gc_shop.levels,gc_shop.contactNo,concat(gc_shop.mtime,'——',gc_shop.atime) as businessTime,gc_shop.professionalBuss,gc_shop.logo as logo,gc_shop.entertainLogo as entertainLogo ,gc_shop.lng ,gc_shop.lat," +
				"(select img from mt_agencyproject as agencyproject where shopid = mt_minormt.shopid and agencyproject.projectid = 11814 and agencyproject.deleted = 0) as img "+
				",mt_minormt.img as imgs, (ACOS(SIN("+lat+" / 180 * PI()) * SIN(gc_shop.lat / 180 * PI()) + COS("+lat+" / 180 * PI()) * COS(gc_shop.lat / 180 * PI()) * COS(("+lng+" - gc_shop.lng) / 180 * PI())) * 180 / PI() * 60 * 1.1515 * 1.609344) AS distance " +
				"from (select * from mt_minormt order by webprice) as mt_minormt INNER JOIN gc_shop on mt_minormt.shopid = gc_shop.shopId where mt_minormt.deleted = 0 AND mt_minormt.carriage = 0 AND mt_minormt.projectid = 0 AND gc_shop.deleted = 0 AND gc_shop.state = 1 ");
		if(!StringUtils.isEmpty(shopName)){
			try {
				sql.append(" AND gc_shop.shopName like '%"+URLDecoder.decode(shopName, "UTF-8")+"%'  ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(deptId != 0){
			sql.append(" AND mt_minormt.deptId = "+ deptId);
		}
		sql.append(" group by mt_minormt.shopid ");
		if(!"0".equalsIgnoreCase(lng) && !"0".equalsIgnoreCase(lat)){
			sql.append(" order by distance ");
		}
		sql.append(" limit "+start+","+row);
		System.out.println(sql.toString());
		List<HashMap> minormtShopList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return minormtShopList;
	}
	
	/**
	 * 查询前三条做小保养的商户类表
	 * @author shendelei
	 * @date 2015-2-28 下午4:13:24
	 * @return
	 */
	public List<HashMap> findMinormtShopList(String shopName, String lng, String lat){
		if(StringUtils.isEmpty(lng)){
			lng = "0";
		}
		if(StringUtils.isEmpty(lat)){
			lat = "0";
		}
		StringBuffer sql = new StringBuffer("select mt_minormt.dmprice,mt_minormt.webprice,gc_shop.shopName,gc_shop.addr,gc_shop.shopId,gc_shop.levels,gc_shop.contactNo,concat(gc_shop.mtime,'——',gc_shop.atime) as businessTime,gc_shop.professionalBuss,gc_shop.logo as logo,gc_shop.entertainLogo as entertainLogo ,gc_shop.lng ,gc_shop.lat," +
				"(select img from mt_agencyproject as agencyproject where shopid = mt_minormt.shopid and agencyproject.projectid = 11814 and agencyproject.deleted = 0) as img "+
				",mt_minormt.img as imgs , (ACOS(SIN("+lat+" / 180 * PI()) * SIN(gc_shop.lat / 180 * PI()) + COS("+lat+" / 180 * PI()) * COS(gc_shop.lat / 180 * PI()) * COS(("+lng+" - gc_shop.lng) / 180 * PI())) * 180 / PI() * 60 * 1.1515 * 1.609344) AS distance " +
				"from (select * from mt_minormt order by webprice) as mt_minormt INNER JOIN gc_shop on mt_minormt.shopid = gc_shop.shopId where mt_minormt.deleted = 0 AND mt_minormt.carriage = 0 AND mt_minormt.projectid = 0 AND gc_shop.deleted = 0 AND gc_shop.state = 1 group by mt_minormt.shopid ");
		if(!"0".equalsIgnoreCase(lng) && !"0".equalsIgnoreCase(lat)){
			sql.append(" order by distance ");
		}
		System.out.println(sql.toString());
		List<HashMap> minormtShopList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return minormtShopList.subList(0, 3);
	}
	
	/**
	 * 查询小保养商户的总数
	 * @author shendelei
	 * @date 2015-3-11 下午4:43:36
	 * @param shopName
	 * @return
	 */
	public int findMinormtShopCount(String shopName, int deptId){
		StringBuffer sql = new StringBuffer("select count(id) as count from (select id from (select * from mt_minormt order by webprice) as mt_minormt INNER JOIN gc_shop on mt_minormt.shopid = gc_shop.shopId where 1=1 ");
		if(!StringUtils.isEmpty(shopName)){
			try {
				sql.append(" AND gc_shop.shopName like '%"+URLDecoder.decode(shopName, "UTF-8")+"%' ");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(deptId != 0){
			sql.append(" AND  mt_minormt.deptId = "+ deptId);
		}
		sql.append(" AND mt_minormt.deleted = 0 AND mt_minormt.carriage = 0 AND mt_minormt.projectid = 0 AND gc_shop.deleted = 0 AND gc_shop.state = 1 group by mt_minormt.shopid) as shopCount");
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(),null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}
	
	/**
	 * 查询某商户的小保养产品
	 * @author shendelei
	 * @date 2015-3-16 上午9:50:41
	 * @return
	 */
	public List<HashMap> findMinormtList(int shopId, int start, int row){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "+
				"minormt.productname,minormt.dmprice,minormt.webprice,minormt.id,minormt.engproductid,minormt.filproductid,minormt.shopid"+
				",CONCAT(`engine`.brandName,'，',`engine`.seriesName,'，',`engine`.apiName,'，',`engine`.saeName,'，',(`engine`.yinumber+`engine`.sinumber*4),'L') as engineName "+
				",CONCAT(`filter`.brandName) as filterName "+
				",(select count(prid) as wb from mt_projectreview a where apId = 11814 and pdId = minormt.id and a.deleted=0) as viewNum "+
				",(select img from mt_agencyproject as agencyproject where shopid = minormt.shopid and agencyproject.projectid = 11814 and agencyproject.deleted = 0) as img "+
				",minormt.img as imgs "+
			"FROM "+
				"mt_minormt AS minormt "+
				"LEFT JOIN mt_engine as `engine` on minormt.engproductid = `engine`.id "+
				"LEFT JOIN mt_filter as `filter` on minormt.filproductid = `filter`.id "+
			"WHERE "+
				"minormt.projectid = 0 "+
				"AND minormt.deleted = 0 "+
				"AND minormt.carriage = 0 "+
				"AND minormt.shopid = "+ shopId +
				" limit "+start+","+row);
		System.out.println(sql.toString());
		List<HashMap> minormtList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return minormtList;
	}
	
	/**
	 * 查询某商户的小保养产品
	 * @author shendelei
	 * @date 2015-3-16 下午2:53:42
	 * @param shopId
	 * @param start
	 * @param row
	 * @return
	 */
	public int findMinormtCount(int shopId){
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) as count "+
					"FROM "+
						"mt_minormt AS minormt "+
					"WHERE "+
						"minormt.projectid = 0 "+
						"AND minormt.deleted = 0 "+
						"AND minormt.carriage = 0 "+
						"AND minormt.shopid = "+ shopId);
		System.out.println(sql.toString());
		List<HashMap> countList = this.getDao().getAllPreBySQL(sql.toString(),null);
		int count = Integer.parseInt(countList.get(0).get("count").toString());
		return count;
	}
	
	/**
	 * 查询机油详细
	 * @author shendelei
	 * @date 2015-3-17 下午1:50:41
	 * @param projectId
	 */
	public List<HashMap> findEngineInfo(int engproductid) {
		String sql = "select mt_productprocedures.* from mt_productprocedures inner join mt_engine on mt_productprocedures.projectId = mt_engine.saeId where mt_engine.id = "+engproductid;
		List<HashMap> engineList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return engineList;
	}
	
	/**
	 * 查询机滤详细
	 * @author shendelei
	 * @date 2015-3-17 下午5:31:15
	 * @param filproductid
	 * @return
	 */
	public List<HashMap> findFilterInfo(int filproductid){
		String sql = "select mt_productprocedures.* from mt_productprocedures inner join mt_filter on mt_productprocedures.projectId = mt_filter.modelid where mt_filter.id = "+filproductid;
		List<HashMap> engineList = this.getDao().getAllPreBySQL(sql.toString(),null);
		return engineList;
	}
	
	/**
	 * 产品评论数量
	 * @author shendelei
	 * @date 2015-3-18 下午3:11:41
	 * @param projectId
	 * @param productId
	 * @return
	 */
	public int findInfoReviewNum(int projectId,int productId){
		StringBuilder sql = new StringBuilder();		
		sql.append("select count(prid) as wb from mt_projectreview where apId="+projectId+" and pdId="+productId+" and deleted=0 ");				
		List<HashMap> r1=(List<HashMap>)this.getDao().getAllBySQL(sql.toString());
		if(!"".equals(r1.get(0).get("wb").toString())) {
			return Integer.parseInt(r1.get(0).get("wb").toString());
		}
		return 0;	
	}
	
//	/**
//	 * 查询所有服务列表页   评价分
//	 */
//	public double findInfoSorce(int shopId,int projectId,int productId){
//		StringBuilder sql = new StringBuilder();
//		sql.append("select sum(apIntegral+apIntegral1+apIntegral2+apIntegral3+apIntegral4)/(5*count(prid)) as wb from mt_projectreview where shopid="+shopId+" and deleted=0 ");		
//		sql.append(" and apid="+projectId+" and pdid="+productId+" ");
//		List<HashMap> r1=(List<HashMap>)this.getDao().getAllBySQL(sql.toString());
//		if(!"".equals(r1.get(0).get("WB").toString())) {
//			return Double.parseDouble(r1.get(0).get("WB").toString()); 
//		}
//		return 0;	
//	}
	
	/**
	 * 详细页评论
	 * @author shendelei
	 * @date 2015-3-18 下午3:11:47
	 * @param projectid
	 * @param productid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<HashMap> findReviewList(int projectid,int productid,int startIndex, int pageSize, String reviewflag) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(reviewflag)){//全部
			sql.append("select b.userName,b.apContent,(select userimg from gc_user where userId =b.userId) as userimg FROM mt_projectreview b where b.deleted=0 and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC limit "+startIndex+","+pageSize);
		}else if("1".equals(reviewflag)){//好评
			sql.append("select b.userName,b.apContent,(select userimg from gc_user where userId =b.userId) as userimg FROM mt_projectreview b where b.deleted=0 and (b.apIntegral=4 or b.apIntegral=5) and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC limit "+startIndex+","+pageSize);
		}else if("2".equals(reviewflag)){//中评
			sql.append("select b.userName,b.apContent,(select userimg from gc_user where userId =b.userId) as userimg FROM mt_projectreview b where b.deleted=0 and (b.apIntegral=2 or b.apIntegral=3) and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC limit "+startIndex+","+pageSize);
		}else if("3".equals(reviewflag)){//差评
			sql.append("select b.userName,b.apContent,(select userimg from gc_user where userId =b.userId) as userimg FROM mt_projectreview b where b.deleted=0 and b.apIntegral=1 and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC limit "+startIndex+","+pageSize);
		}
		System.out.println(sql);
		List<HashMap> prList = this.getDao().getAllPreBySQL(sql.toString(),null);
		String userName = null;
		String strSubstring = null;
		for(int i=0;i<prList.size();i++){
			userName = prList.get(i).get("userName").toString();
			if (userName != null&& !userName.equals("")) { 				
				Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");//正则匹配手机号码				
				Matcher m = p.matcher(userName);  
				m.matches();		
				if(Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(userName).find()){//正则匹配手机号码
					strSubstring = userName.substring(3,8);
					for (int k = 0; k < strSubstring.length(); k++) {
						strSubstring=strSubstring.replace(strSubstring.charAt(k), '*');
					}
					userName = userName.substring(0, 3) + strSubstring+userName.substring(8);				
				} else if (Pattern.compile("[\u4e00-\u9fa5]").matcher(userName.subSequence(0, 1)).find()) {// 正则匹配中文(以中文开头)
					strSubstring = userName.substring(1);
					for (int k = 0; k < strSubstring.length(); k++) {
						strSubstring = strSubstring.replace(strSubstring.charAt(k), '*');
					}
					userName = userName.substring(0, 1) + strSubstring;
				}else{//字母 数字 中文的组合(保留第一个和最后一个)
					if(userName.length()>2){
						strSubstring = userName.substring(1,userName.length()-1);
						for (int k = 0; k < strSubstring.length(); k++) {
							strSubstring = strSubstring.replace(strSubstring, "***");
						}
						userName = userName.substring(0, 1) + strSubstring+userName.substring(userName.length()-1);
					}		
				}
			}
			prList.get(i).put("userName", userName);
		}
		return prList;
	}
	
	/**
	 * 产品评论数量（好评）
	 * 
	 * @return
	 */				
	public double getInfoViewNumHao(int shopid,int projectid,int productid){
		StringBuilder sql = new StringBuilder();		
//		sql.append("select count(prid) as wb from mt_projectreview where and (apIntegral=4 or apIntegral=5) and apId="+projectid+" and pdId="+productid+" and deleted=0 ");
		sql.append("select count(*) as wb FROM mt_projectreview b where b.deleted=0 and (b.apIntegral=4 or b.apIntegral=5) and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC ");
		List<HashMap> r1=(List<HashMap>)this.getDao().getAllBySQL(sql.toString());
		if(r1.get(0).get("wb")!=null) {
			return Double.parseDouble(r1.get(0).get("wb").toString());
		}
		return 0;	
	}
	
	/**
	 * 产品评论数量（差评）
	 * 
	 * @return
	 */				
	public double getInfoViewNumCha(int shopid,int projectid,int productid){
		StringBuilder sql = new StringBuilder();		
		sql.append("select count(*) as wb FROM mt_projectreview b where b.deleted=0 and b.apIntegral=1 and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC ");
		List<HashMap> r1=(List<HashMap>)this.getDao().getAllBySQL(sql.toString());
		if(r1.get(0).get("wb")!=null) {
			return Double.parseDouble(r1.get(0).get("wb").toString());
		}
		return 0;	
	}
	/**
	 * 产品评论数量（中评）
	 * 
	 * @return
	 */				
	public double getInfoViewNumZhong(int shopid,int projectid,int productid){
		StringBuilder sql = new StringBuilder();		
		sql.append("select count(*) as wb FROM mt_projectreview b where b.deleted=0 and (b.apIntegral=2 or b.apIntegral=3) and b.apId='"+projectid+"' and b.pdId='"+productid+"' ORDER BY createtime DESC ");
		List<HashMap> r1=(List<HashMap>)this.getDao().getAllBySQL(sql.toString());
		if(r1.get(0).get("wb")!=null) {
			return Double.parseDouble(r1.get(0).get("wb").toString());
		}
		return 0;	
	}
	
	/**
	 * 获得好评度
	 * @author shendelei
	 * @date 2015-3-18 下午5:10:59
	 * @return
	 */
	public String getRating(int shopid,int projectid,int productid){
		double chaChild1 = getInfoViewNumCha(shopid, projectid, productid);
		double zhongChild1 = getInfoViewNumZhong(shopid, projectid, productid);
		double haoChild1 = getInfoViewNumHao(shopid, projectid, productid);
		int haoChild = (int) haoChild1;
		DecimalFormat df = new DecimalFormat("#");
		if(haoChild==0){
			//好评百分比
		    return "0";
		}else{
			double hao1 = (haoChild1/(chaChild1 + zhongChild1 + haoChild1))*100;
			return df.format(hao1);
		}
	}
	/**
	 * @Description: 根据主键查询小保养	
	 * @param id
	 * @return
	 */
	public MtMinormt findMinormtById(int id){
		return (MtMinormt) this.getDao().findIObjectByPK(MtMinormt.class, id);
	}

}
