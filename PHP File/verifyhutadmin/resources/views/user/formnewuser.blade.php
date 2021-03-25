@extends('layouts/main');
@section('content')
<h1>INSERT NEW USER</h1>
<form method='POST' action='user_p_insertuser'>
	Nama : <input type='text' name='nama'><br>
	No Matrik : <input type='text' name='nomatrik'><br>
	Email : <input type='text' name='email'><br>
	Password : <input type='password' name='password' value='123'><br>
	<input type='submit' name='btn' value='ADD'>
	<input type="hidden" name="_token" value="{{ csrf_token() }}">
</form>
@endsection