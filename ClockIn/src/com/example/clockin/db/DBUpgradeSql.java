package com.example.clockin.db;

import java.util.HashMap;

public class DBUpgradeSql {
	/**
	 * 脚本编写规则，
	 * 在每次数据库升级的时候，
	 * 按版本号添加在相应位置处，
	 * 出现多条脚本时用"|"竖线分开如：
	 * put(2, " alter table_name add column_name varchar(12)|update table_name set column_name =\"example\"");
	 *   
	 */
	HashMap<Integer, String> DBUpgradeScript = new HashMap<Integer, String>() {
		{
			put(1, "");// 数据库版本1为基础数据库，无需添加版本变更脚本，直接留空
			put(2, "CREATE TABLE IF NOT EXISTS t_grid_icons ( system_code varchar, service_id integer primary key autoincrement, is_show integer, listorder integer, image_url varchar, service_name varchar)|CREATE TABLE IF NOT EXISTS t_goods_cache ( cache_id integer primary key autoincrement, is_show integer, goods_price varchar, listorder integer, image_url varchar, gid integer, goods_name varchar, catid integer)|CREATE TABLE IF NOT EXISTS t_goods_category ( is_show integer, listorder integer, catid integer, catname varchar)|CREATE TABLE IF NOT EXISTS t_shopping_car ( img_url varchar, uid integer, item_num integer, goods_price varchar, goods_no varchar, goods_id integer primary key autoincrement, goods_name varchar, ischecked integer, date varchar)|CREATE TABLE IF NOT EXISTS t_member ( is_online integer, img_url varchar, uid integer, username varchar, accessToken varchar, lastTime varchar, expires_in integer, mem_id integer primary key autoincrement)|CREATE TABLE IF NOT EXISTS t_trainket_search_history ( start_place varchar, end_place varchar, go_date varchar, search_id integer primary key autoincrement)");
			put(3, "");
			put(4, "");
			put(5, "");
			put(6, "");
		}
	};

	public HashMap<Integer, String> getUpdateDBScript() {
		return DBUpgradeScript;
	}
}
