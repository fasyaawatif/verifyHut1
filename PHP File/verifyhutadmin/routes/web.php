<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get("user","MyController@viewuser");
Route::get("user_insertnewuser",function(){
	return view('user.formnewuser');
});
Route::post("user_p_insertuser","MyController@insertnewuser");

//LOGIN
Route::get("dologin",function(){
	return view('formLogin');
});

Route::post("login",'MyController@login');
Route::get('logout','MyController@logout');
//-----------------------
Route::get('/', function () {
	return view('layouts/main');
});

Route::get("page_bookitem","MyController@viewAllItems");
Route::post("newbooking","MyController@newbooking");
Route::post("newbooking_add","MyController@newbooking_add");
Route::post("statusbooking","MyController@statusbooking");




Route::get("productstatusbooking","MyController@viewAllProductStatus");
Route::get("manufacturerstatusbooking","MyController@viewAllManufacturerStatus");

Route::post("productstatus_approve","MyController@approveproductstatus");
Route::post("productstatus_reject","MyController@rejectproductstatus");

Route::post("manufacturerstatus_approve","MyController@approvemanufacturerstatus");
Route::post("manufacturerstatus_reject","MyController@rejectmanufacturerstatus");









Route::get("/A2",function(){
	$str = "SUHAILAN";
	$msg = "SELAMAT DATANG";
	return view('modul2.modul2view',compact('str','msg'));
});



Route::get("/A1",function(){
	return view('modul1.viewModul1');
});


Route::get('/A', function () {
	return view('main'); //main.php in resources/views
});

Route::get('/B', function () {
	$a = "<a href='www.unisza.edu.my'>UniSZA</a>";
	return $a;
});

Route::get('/C', 'MyController@CCC');













Route::get('/index', 'MyController@index');

Route::get('/home', function () {
	return 'Welcome to MyController /home';
});

