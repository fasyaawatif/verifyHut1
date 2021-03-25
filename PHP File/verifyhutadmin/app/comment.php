<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class comment extends Model{
	public function x(){
		return $this->belongsTo('App\pengguna','users_id','id');
	}
}