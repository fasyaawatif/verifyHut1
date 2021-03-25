@extends('layouts/main')
@section('content')

<h1> PRODUCT PROCESS</h1>

<table border=1>

<tr>
<th> PRODUCT ID </th>
<th> PRODUCT NAME </th>
<th> BRAND </th>
<th> DESCRIPTION </th>
<th> TYPE </th>
<th> INGREDIENT </th>
<th> HALAL REFERENCE </th>
<th> STATUS </th>
<th> ACTION </th>
</tr>

@foreach ($product as $u)
<tr>
<td style = 'color red'> {{$u->productID}} </td>
<td>{{$u->productName}}</td>
<td>{{$u->brand}}</td>
<td>{{$u->productDesc}}</td>
<td>{{$u->type}}</td>
<td>{{$u->ingredient}}</td>
<td>{{$u->halalCertified}}</td>
@if ($u-> status=='New')
	<td> New </td>
@elseif ($u-> status=='Approved')
	<td> Approved </td>
@elseif ($u-> status=='Rejected')
	<td> Rejected </td>
@endif

<td>
<form METHOD = 'POST' action = 'productstatus_approve'>
<input type ='hidden' name = 'productID' value ='{{$u->productID}}'>
<input type ='submit' name = 'btn' value = 'Approve'>
{!! csrf_field() !!}
</form>

<form METHOD = 'POST' action = 'productstatus_reject'>
<input type ='hidden' name = 'productID' value ='{{$u->productID}}'>
<input type ='submit' name = 'btn' value = 'Reject'>
{!! csrf_field() !!}
</form>

</td>

</tr>
@endforeach
</table>
@endsection