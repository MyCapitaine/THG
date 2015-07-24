package org.thg.logic.story.api;
/**
 * <p>记录进度以及flag的文件</p>
 * <p>提供对进度和flag的一些操作</p>
 * <p>以及文件的存取和比较</p>
 * @author MyCapitaine
 *
 */
public interface ProgressData {
	/**
	 * 设置某个flag的值
	 * @param key flag名称，一般以日期命名
	 * @param num flag选择，0为未选
	 * @return true 操作成功
	 */
	public boolean putFlag(String key, int num);
	/**
	 * 获取某个flag的值
	 * @param key flag的名称
	 * @return -1为未取到
	 */
	public int getFlag(String key);
	/**
	 * 增加/减少某项印象值1点
	 * @param key 印象值名称
	 * @param plus true 增加
	 * @return true 操作成功
	 */
	public boolean putValue(String key, boolean plus);
	/**
	 * 获取印象值
	 * @param key 印象值名称
	 * @return -1为未取到
	 */
	public int getValue(String key);
	/**
	 * 读取默认的进度数据，即空数据
	 */
	public void load();
	/**
	 * <p>读取指定url下的进度数据</p>
	 * <p>不希望重复读取</p>
	 * @param url 将要读取数据的地址
	 */
	public void load(String url);
	/**
	 * 将进度数据保存在指定的url下
	 * @param url 将要保存数据的地址
	 */
	public void save(String url);
	/**
	 * 设置进度中的日期和场景编号以及段落编号
	 * @param dayStr 日期
	 * @param sceneNum 场景编号
	 * @param dialogNum 段落编号
	 * @return false 设置失败
	 */
	public boolean putTime(String dayStr, int sceneNum, int dialogNum);
	
	/**
	 * 匹配两个进度文件中的key值以判断是否有文件损坏
	 * @param pd 另一个数据文件
	 * @return false 其中一个文件已经损坏
	 */
	public boolean match(ProgressData pd);
	/**
	 * 返回进度数据中是否含有某项flag或某项感情度
	 * @param key flag或感情度名称
	 * @return true 存在
	 */
	public boolean containsKey(String key);
	/**
	 * 获取进度数据中的日期字符
	 * @return 日期字符
	 */
	public String getDayStr();
	/**
	 * 获取进度数据中场景下标
	 * @return 场景下标
	 */
	public int getSceneNum();
	/**
	 * 获取进度数据中对话下标
	 * @return 对话下标
	 */
	public int getDialogNum();
	
	/**
	 * 存储时间，当前句子等信息到进度数据
	 * @param info 要存储的额外的信息
	 */
	public void putExtraInfo(String info);
	/**
	 * 获取额外信息
	 * @return 额外的信息
	 */
	public String getExtraInfo();
	
	/**
	 * 获取该存档的一份拷贝
	 * @return 拷贝
	 */
	public ProgressData copy();
}
