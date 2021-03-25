@extends('layouts/main')
@section('content')
<h1>STATUS BOOKING</h1>
<a href='logout'>Logout</a>
<h2>Welcome {{ Session::get('pengguna') }} - {{ Session::get('nama') }}</h2>

<table border=1>
@foreach ($item as $u)
	<tr>
		<td style='color:red'> {{$u->id_item}} </td>
		<td> {{$u->username}} </td>
		<td> {{$u->date_book}} </td>
		<td> {{$u->description}} </td>
		@if ($u->status==0)
			<td> New </td>
		@elseif ($u->status==1)
			<td> Approve </td>
		@elseif ($u->status==2)
			<td> Reject </td>
		@endif
		<td> {{$u->notes}} </td>
	</tr>
@endforeach
</table>
@endsection