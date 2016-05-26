/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.clockin.common.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.clockin.R;


public class CommonAlertDialog extends Dialog {

	public CommonAlertDialog(Context context, int theme) {
		super(context, theme);
	}

	public CommonAlertDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String leftButtonText;
		private String middleButtonText;
		private String rightButtonText;
		private boolean cancleAble;
		private View contentView;

		private DialogInterface.OnClickListener leftButtonClickListener,
				middleButtonClickListener, rightButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * 根据字符串创建提示
		 * 
		 * @param message
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * 根据resourceId创建提示
		 * 
		 * @param message
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * 根据字符串创建标题
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setCancleAble(boolean cancleAble) {
			this.cancleAble = cancleAble;
			return this;
		}

		/**
		 * 根据resourceId创建标题
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * 创建左边按钮
		 * 
		 * @param leftButtonText
		 * @param listener
		 * @return
		 */
		public Builder setLeftButton(int leftButtonText,
				DialogInterface.OnClickListener listener) {
			this.leftButtonText = (String) context.getText(leftButtonText);
			this.leftButtonClickListener = listener;
			return this;
		}

		public Builder setLeftButton(String leftButtonText,
				DialogInterface.OnClickListener listener) {
			this.leftButtonText = leftButtonText;
			this.leftButtonClickListener = listener;
			return this;
		}

		/**
		 * 创建中间按钮
		 * 
		 * @param middleButtonText
		 * @param listener
		 * @return
		 */
		public Builder setMiddleButton(int middleButtonText,
				DialogInterface.OnClickListener listener) {
			this.middleButtonText = (String) context.getText(middleButtonText);
			this.middleButtonClickListener = listener;
			return this;
		}

		public Builder setMiddleButton(String middleButtonText,
				DialogInterface.OnClickListener listener) {
			this.middleButtonText = middleButtonText;
			this.middleButtonClickListener = listener;
			return this;
		}

		/**
		 * 创建右边按钮
		 * 
		 * @param rightButtonText
		 * @param listener
		 * @return
		 */
		public Builder setRightButton(int rightButtonText,
				DialogInterface.OnClickListener listener) {
			this.rightButtonText = (String) context.getText(rightButtonText);
			this.rightButtonClickListener = listener;
			return this;
		}

		public Builder setRightButton(String rightButtonText,
				DialogInterface.OnClickListener listener) {
			this.rightButtonText = rightButtonText;
			this.rightButtonClickListener = listener;
			return this;
		}

		public CommonAlertDialog create() {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final CommonAlertDialog dialog = new CommonAlertDialog(context,R.style.MyAlertDialog);

			View layout = inflater.inflate(R.layout.dialog_social_delete,
					null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

			// 设置左边按钮
			if (leftButtonText != null) {
				((TextView) layout.findViewById(R.id.tv_left))
						.setText(leftButtonText);
				if (leftButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.tv_left))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									leftButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				layout.findViewById(R.id.tv_left).setVisibility(View.GONE);
			}
			// 设置中间按钮
			if (middleButtonText != null) {
				((TextView) layout.findViewById(R.id.tv_middle))
						.setText(middleButtonText);
				if (middleButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.tv_middle))
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									middleButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEUTRAL);
								}
							});
				}
			} else {
				layout.findViewById(R.id.tv_middle).setVisibility(View.GONE);
			}
			// 设置标题
			if (title != null) {
				((TextView) layout.findViewById(R.id.tv_title)).setText(title);
			} else {
				((TextView) layout.findViewById(R.id.tv_message)).setText("提示");
			}
			// 设置右边按钮
			if (rightButtonText != null) {
				((TextView) layout.findViewById(R.id.tv_right))
						.setText(rightButtonText);
				if (rightButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.tv_right))
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									rightButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				layout.findViewById(R.id.tv_right).setVisibility(View.GONE);
			}
			// 设置消息
			if (message != null) {
				((TextView) layout.findViewById(R.id.tv_message))
						.setText(message);
			} else if (contentView != null) {
				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));

			}
			dialog.setCanceledOnTouchOutside(cancleAble);
			dialog.setCancelable(cancleAble);
			dialog.setContentView(layout);
			return dialog;

		}
	}
}
