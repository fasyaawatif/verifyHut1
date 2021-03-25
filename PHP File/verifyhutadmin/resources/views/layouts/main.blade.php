<link rel = "stylesheet"  type = "text/css"  
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">

<div class="container">
	 <div class="row" style='background-color:grey'>
		<div class="col-12 text-center">@include('layouts/header')</div>
	 </div>
	 <div class="row" style='background-color:#34D5AC'>
		<div class="col-3" style='background-color:#E8DAEF'>@include('layouts/menu')</div>
	    <div class="col-9">@yield('content')</div>
		
	</div>
	<div class="row"  style='background-color:grey'>
		<div class="col-12 text-center">VerifyHut</div>
	</div>
</div>