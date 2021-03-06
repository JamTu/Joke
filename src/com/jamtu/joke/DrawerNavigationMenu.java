package com.jamtu.joke;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jamtu.interfaces.DrawerMenuCallBack;
import com.jamtu.widget.BadgeView;
import com.jamtu.widget.CircleImageView;

/**
 * 导航菜单栏
 * 
 * @created 2015-03-29
 * @author lijq
 * 
 */
public class DrawerNavigationMenu extends Fragment implements OnClickListener {

	public static DrawerNavigationMenu newInstance() {
		return new DrawerNavigationMenu();
	}

	private View mSavedView;// 当前操作的菜单项
	private RelativeLayout mMenu_user_layout;
	private LinearLayout mMenu_user_info_layout;
	private LinearLayout mMenu_user_login_tips;
	private CircleImageView mUser_info_userface;
	private TextView mUser_info_username;

	private LinearLayout mMenu_item_explore;
	private LinearLayout mMenu_item_myself;
	private LinearLayout mMenu_item_language;
	private LinearLayout mMenu_item_shake;
	private LinearLayout mMenu_item_setting;
	private View mMenu_item_exit;

	private DrawerMenuCallBack mCallBack;

	public static BadgeView mNotification_bv;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof DrawerMenuCallBack) {
			mCallBack = (DrawerMenuCallBack) activity;
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallBack = null;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		setupUserView(false);
	}

	private void setupUserView(final boolean reflash) {
		// 判断是否已经登录，如果已登录则显示用户的头像与信息
		mUser_info_userface.setImageResource(R.drawable.mini_avatar);
		mUser_info_username.setText("123");
		mMenu_user_info_layout.setVisibility(View.GONE);
		mMenu_user_login_tips.setVisibility(View.VISIBLE);
		return;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_main_drawer_menu, null);
	}

	private void initBadgeView(View view) {
	}

	// 初始化界面控件
	private void initView(View view) {
		mMenu_user_layout = (RelativeLayout) view.findViewById(R.id.menu_user_layout);
		mMenu_user_info_layout = (LinearLayout) view.findViewById(R.id.menu_user_info_layout);
		mUser_info_userface = (CircleImageView) view.findViewById(R.id.menu_user_info_userface);
		mUser_info_username = (TextView) view.findViewById(R.id.menu_user_info_username);
		mMenu_user_login_tips = (LinearLayout) view.findViewById(R.id.menu_user_info_login_tips_layout);

		mMenu_item_explore = (LinearLayout) view.findViewById(R.id.menu_item_explore);
		mMenu_item_myself = (LinearLayout) view.findViewById(R.id.menu_item_myself);
		mMenu_item_language = (LinearLayout) view.findViewById(R.id.menu_item_language);
		mMenu_item_shake = (LinearLayout) view.findViewById(R.id.menu_item_shake);
		mMenu_item_setting = (LinearLayout) view.findViewById(R.id.menu_item_setting);
		mMenu_item_exit = view.findViewById(R.id.menu_item_exit);

		// 绑定点击事件
		mMenu_user_layout.setOnClickListener(this);
		mMenu_item_explore.setOnClickListener(this);
		mMenu_item_myself.setOnClickListener(this);
		mMenu_item_language.setOnClickListener(this);
		mMenu_item_shake.setOnClickListener(this);
		mMenu_item_setting.setOnClickListener(this);
		mMenu_item_exit.setOnClickListener(this);

		// 高亮发现菜单栏
		highlightSelectedItem(mMenu_item_explore);
		initBadgeView(view);
	}

	private void highlightSelectedItem(View v) {
		setSelected(null, false);
		setSelected(v, true);
	}

	public void highlightExplore() {
		highlightSelectedItem(mMenu_item_explore);
	}

	private void setSelected(View v, boolean selected) {
		View view;
		if (v == null && mSavedView == null) {
			return;
		}
		if (v != null) {
			mSavedView = v;
			view = mSavedView;
		} else {
			view = mSavedView;
		}
		if (selected) {
			ViewCompat.setHasTransientState(view, true);
			view.setBackgroundColor(getResources().getColor(R.color.menu_layout_item_pressed_color));
		} else {
			ViewCompat.setHasTransientState(view, false);
			view.setBackgroundResource(R.drawable.menu_layout_item_selector);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.menu_user_layout:
			onClickLogin();
			break;
		case R.id.menu_item_explore:
			onClickExplore();
			highlightSelectedItem(v);
			break;
		case R.id.menu_item_myself:
			onClickMySelf();
			highlightSelectedItem(v);
			break;
		case R.id.menu_item_language:
			onClickLanguage();
			highlightSelectedItem(v);
			break;
		case R.id.menu_item_shake:
			onClickmShake();
			break;
		case R.id.menu_item_setting:
			onClickSetting();
			break;
		case R.id.menu_item_exit:
			onClickExit();
			break;
		}
	}

	private void onClickLogin() {
		if (mCallBack != null) {
			mCallBack.onClickLogin();
		}
	}

	private void onClickSetting() {
		if (mCallBack != null) {
			mCallBack.onClickSetting();
		}
	}

	private void onClickExplore() {
		if (mCallBack != null) {
			mCallBack.onClickExplore();
		}
	}

	private void onClickMySelf() {
		if (mCallBack != null) {
			mCallBack.onClickMySelf();
		}
	}

	private void onClickLanguage() {
		if (mCallBack != null) {
			mCallBack.onClickLanguage();
		}
	}

	private void onClickmShake() {
		if (mCallBack != null) {
			mCallBack.onClickShake();
		}
	}

	private void onClickExit() {
		if (mCallBack != null) {
			mCallBack.onClickExit();
		}
	}
}
