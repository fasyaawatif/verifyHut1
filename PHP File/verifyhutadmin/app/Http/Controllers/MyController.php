<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;

class MyController extends Controller
{
    public function logout(){
		session()->flush();
		return redirect('dologin');
	}
	
	public function login(Request $r){
		$adminID=  $r->u;
		$password = $r->p;
		$password = sha1($password);
		$result = \App\user::where("adminID","=",$adminID)
				->where("password","=",$password)
				->get();
		if ($result->count()>0){
			echo "Berjaya";
			session(['pengguna' => $adminID,
					'level'=>$result[0]->level
					]);
			return redirect('user');
		}else{
			echo "Not a valid login";			
		}
	}
	
	
	public function viewuser(){
		if (session('pengguna')){
			if (session('level')==1){
				$listofuser = \App\user::all();
			}else{
				$listofuser = \App\user::where('nomatrik','=',session('pengguna'))
							->get();
			}			
			return view('viewUser',compact('listofuser'));		
		}else{
			echo "Please login first!";
		}
	}
	
	
	public function viewAllProductStatus(Request $r){
		if (session('pengguna')){
			$product = \App\product::all();
			return view('product/bookProduct',compact('product'));		
		}else{
			echo "Please login first!";
		}
	}
	
	public function viewAllManufacturerStatus(Request $r){
		if (session('pengguna')){
			$manufacturer = \App\manufacturer::all();
			return view('manufacturer/bookManufacturer',compact('manufacturer'));		
		}else{
			echo "Please login first!";
		}
	}

	
	public function approveproductstatus(Request $u){
		$result = \App\product::where('productID','=',$u->productID)
					->update(['status'=>"Approved"]);
					
		if ($result){
			echo "Product approved";
			return redirect('productstatusbooking');
		}else{
			echo "Failed to approve";
		}
	}
	
	public function rejectproductstatus(Request $u){
		$result = \App\product::where('productID','=',$u->productID)
					->update(['status'=>"Rejected"]);
					
		if ($result){
			echo "Product rejected";
			return redirect('productstatusbooking');
		}else{
			echo "Failed to reject";
		}
	}
	
	public function approvemanufacturerstatus(Request $u){
		$result = \App\manufacturer::where('ssmID','=',$u->ssmID)
					->update(['status'=>"Approved"]);
					
		if ($result){
			echo "Manufacturer approved";
			return redirect('manufacturerstatusbooking');
		}else{
			echo "Failed to approve";
		}
	}
	
	public function rejectmanufacturerstatus(Request $u){
		$result = \App\manufacturer::where('ssmID','=',$u->ssmID)
					->update(['status'=>"Rejected"]);
					
		if ($result){
			echo "Manufacturer rejected";
			return redirect('manufacturerstatusbooking');
		}else{
			echo "Failed to reject";
		}
	}
}