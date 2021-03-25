@extends('layouts/main');
@section('content')
<h1>ALL ITEM</h1>
<table border=1>
@foreach ($item as $b)
	<tr>
		<td> {{$b->name}} </td>
		<td> {{$b->description}} </td>
		<td> 
			<form action='newbooking' method='POST'>
			<input type='hidden' name='id' value="{{$b->id}}">
			<input type='submit' name='btn' value='New Book'>
			{!! Form::token() !!}		
			</form>
			
			<form action='statusbooking' method='POST'>
			<input type='hidden' name='id' value="{{$b->id}}">
			<input type='submit' name='btn' value='View Status'>
			{!! Form::token() !!}		
			</form>
		</td>		
	</tr>
@endforeach
</table>
@endsection