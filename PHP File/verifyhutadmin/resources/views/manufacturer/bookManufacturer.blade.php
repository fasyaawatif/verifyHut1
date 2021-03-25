@extends('layouts/main')
@section('content')

<h1> MANUFACTURER PROCESS</h1>

<table border=1>

<tr>
<th> SSM NUM </th>
<th> EMAIL </th>
<th> NAME </th>
<th> DESCRIPTION </th>
<th> PHONE </th>
<th> ADDRESS </th>
<th> STATUS </th>
<th> ACTION </th>
</tr>

@foreach ($manufacturer as $u)
<tr>
<td style = 'color red'> {{$u->ssmID}} </td>
<td>{{$u->email}}</td>
<td>{{$u->name}}</td>
<td>{{$u->description}}</td>
<td>{{$u->phone}}</td>
<td>{{$u->address}}</td>
@if ($u-> status == 'New')
<td> New </td>
@elseif ($u-> status == 'Approved')
<td> Approved </td>
@elseif ($u-> status == 'Rejected')
<td> Rejected </td>
@endif

<td>
<form METHOD = 'POST' action = 'manufacturerstatus_approve'>
<input type ='hidden' name = 'ssmID' value ='{{$u->ssmID}}'>
<input type ='submit' name = 'btn' value = 'Approve'>
{!! csrf_field() !!}
</form>

<form METHOD = 'POST' action = 'manufacturerstatus_reject'>
<input type ='hidden' name = 'ssmID' value ='{{$u->ssmID}}'>
<input type ='submit' name = 'btn' value = 'Reject'>
{!! csrf_field() !!}
</form>

</td>

</tr>
@endforeach
</table>
<br>
<br>
<br>
<br>
@endsection