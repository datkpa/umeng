package com.geequlim.gdutils.umeng;


import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.commonsdk.UMConfigure;

import org.godotengine.godot.Dictionary;
import org.godotengine.godot.Godot;
import org.godotengine.godot.GodotLib;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

public class Umeng extends Godot.SingletonBase {

	private Activity activity = null;

	static public Godot.SingletonBase initialize(Activity p_activity) {
		return new Umeng(p_activity);
	}

	public Umeng(Activity p_activity) {

		this.activity = p_activity;

		UMGameAgent.setScenarioType(p_activity, MobclickAgent.EScenarioType.E_UM_GAME);
		final String appKey = GodotLib.getGlobal("umeng/app_key.Android");
		final String channel = GodotLib.getGlobal("umeng/channel.Android");
		final boolean debug = GodotLib.getGlobal("umeng/debug.Android").equals("True");
		final boolean one_scene = GodotLib.getGlobal("umeng/one_scene.Android").equals("True");

		UMConfigure.setLogEnabled(debug);
		if (debug) {
			Log.d("Umeng", "AppKey: " + appKey + "  Channel: " + channel);
			Log.d("Umeng", "Device Info: " + Utils.getDeviceInfo(p_activity));
		}

		MobclickAgent.openActivityDurationTrack(one_scene);
		UMConfigure.init(p_activity, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, null);

		//register class name and functions to bind
		registerClass("Umeng", new String[]{
				"on_scene_start",
				"on_scene_end",
				"on_event",
				"on_event_with_params",
				"on_profile_sign_in",
				"on_profile_sign_off",
				"fail_level",
				"finish_level",
				"start_level",
				"buy_item",
				"pay_coin",
				"pay_item",
				"exchange",
				"use_item",
				"bonus_coin",
				"bonus_item",
				"report_error",
		});
	}

	protected void onMainActivityResult(int requestCode, int resultCode, Intent data) {}

	protected void onMainPause() {
		super.onMainPause();
		MobclickAgent.onPause(this.activity);
	}

	protected void onMainResume() {
		super.onMainResume();
		MobclickAgent.onResume(this.activity);
	}

	protected void onMainDestroy() {}

	public void on_scene_start(final String page) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onPageStart(page);
			}
		});

	}

	public void on_scene_end(final String page) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onPageEnd(page);
			}
		});
	}

	public void on_event(final String id, final String label) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (label == null || label.length() == 0)
					MobclickAgent.onEvent(activity, id);
				else
					MobclickAgent.onEvent(activity, id, label);
			}
		});
	}

	public void on_event_with_params(final String id, final Dictionary dict) {

		final HashMap<String, String> params = new HashMap<String, String>();
		String[] keys = dict.get_keys();
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			String value = dict.get(key).toString();
			params.put(key, value);
		}

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onEvent(activity, id, params);
			}
		});
	}

	public void on_profile_sign_in(final String provider, final String id) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.onProfileSignIn(provider, id);
			}
		});
	}

	public void on_profile_sign_off() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.onProfileSignOff();
			}
		});
	}

	public void start_level(final String id) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.startLevel(id);
			}
		});
	}

	public void finish_level(final String id) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.finishLevel(id);
			}
		});
	}

	public void fail_level(final String id) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.failLevel(id);
			}
		});
	}

	public void buy_item(final String item, final int number, final double price) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.buy(item, number, price);
			}
		});
	}

	public void pay_coin(final double money, final double coin, final int source) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.pay(money, coin, source);
			}
		});
	}

	public void pay_item(final double money, final String item , final int number, final double price, final int source) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.pay(money, item, number, price, source);
			}
		});
	}

	public void exchange(final double currencyAmount, final String currencyType, final double virtualAmount, final int channel, final String orderId) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.exchange(currencyAmount, currencyType, virtualAmount, channel, orderId);
			}
		});
	}

	public void use_item(final String item, final int number, final double price) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.use(item, number, price);
			}
		});
	}

	public void bonus_coin(final double coin, final int trigger) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.bonus(coin, trigger);
			}
		});
	}

	public void bonus_item(final String item, final int num, final double price, final int trigger) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.bonus(item, num, price, trigger);
			}
		});
	}

	public void report_error(final String error) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				UMGameAgent.reportError(activity, error);
			}
		});
	}

	protected void onGLDrawFrame(GL10 gl) {}
	protected void onGLSurfaceChanged(GL10 gl, int width, int height) {
		UMGameAgent.setOpenGLContext(gl);
	}




}
