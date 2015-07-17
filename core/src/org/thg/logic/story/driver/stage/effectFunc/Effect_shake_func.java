package org.thg.logic.story.driver.stage.effectFunc;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Effect_shake_func {
	/** 在时限内摇晃的次数 */
	private static final float FREQUENCE = 5f;
	/** 摇晃的幅度与自长的比例 */
	private static final float AMPLITUDE = 0.1f;
	/** 随机数生成种，用以控制角度 */
	private static long SEED = 11111111L;
	public static void setSeed(long seed) {
		SEED = seed;
	}
	
	public static void effect_misty(final Actor actor, final float timeCount, final float limitTimeCount,
			final float x, final float y) {
		
		float interval = limitTimeCount / FREQUENCE; //每次间隔时间计数
		int current = (int)(timeCount / interval); //已经震过的次数
		float rate = (timeCount - current * interval) / interval; //现在正在进行的震动的进行比例
		Random r = new Random(SEED);
		for(int i = 0; i < current; i ++)
			r.nextDouble();
		double angle = r.nextDouble() * Math.PI * 2; //本次震动的角度
		
//		float dx_max = (float)Math.cos(angle) * AMPLITUDE,
//			dy_max = (float)Math.sin(angle) * AMPLITUDE ;
		
		
		
		
		
	}
}
