package org.thg.logic.factorys;

import org.thg.logic.story.api.GArticleScene;
import org.thg.logic.story.api.GArticleStage;
import org.thg.logic.story.api.GDay;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GEffectStage;
import org.thg.logic.story.api.GGameStage;
import org.thg.logic.story.api.GScene;
import org.thg.logic.story.api.GTurnPageStage;
import org.thg.logic.story.driver.stage.DefaultArticleStage;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.DefaultGameStage;
import org.thg.logic.story.driver.stage.DefaultTurnPageStage;

public class GStageFactory {
	
	public static GGameStage createGameStage(GScene scene, GDialog dialog) {
		DefaultGameStage dgs = new DefaultGameStage();
		dgs.setBg(scene.getBackground());
		dgs.setBgm(scene.getBgMusic());
		dgs.setDialog(dialog);
		
		return dgs;
	}
	
	public static GTurnPageStage createTurnPageStage(GDay d) {
		return new DefaultTurnPageStage(d.getDayStr());
	}
	public static GEffectStage createEffectStage(GEffectScene eScene) {
		DefaultEffectStage es = new DefaultEffectStage();
		es.setScene(eScene);
		return es;
	}
	
	public static GArticleStage createArticleStage(GArticleScene aScene) {
		return createArticleStage(aScene, aScene.getNextDialog());
	}
	public static GArticleStage createArticleStage(GArticleScene aScene, GDialog dialog) {
		DefaultArticleStage das = new DefaultArticleStage();
		das.setScene(aScene);
		das.setBg(aScene.getBackground());
		das.setBgm(aScene.getBgMusic());
		if(dialog != null) das.setDialog(dialog);
		return das;
	}
}
