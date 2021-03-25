@extends('layouts/main');
@section('content')
<form action='newbooking_add' method='POST'>
<h1>ALL ITEM</h1>
<table border=1>
@foreach ($item as $b)
	<tr>
		<td> NAME </td>
		<td> {{$b->name}} </td>
	</tr>
	<tr>
		<td> DESCRIPTION </td>
		<td> {{$b->description}} </td>
	</tr>
	<tr>
		<td> DATE </td>
		<td> <input type='date' name='date' required> </td>
	</tr>
	<tr>
		<td> KOMEN </td>
		<td> <input type='text' name='komen' required> </td>
	</tr>
	<tr>
		<td> 		
			<input type='hidden' name='id_item' value="{{$b->id}}">
			<input type='submit' name='btn' value='Book'>
			{!! Form::token() !!}			
		</td>		
	</tr>
</form>			
@endforeach
</table>
@endsection