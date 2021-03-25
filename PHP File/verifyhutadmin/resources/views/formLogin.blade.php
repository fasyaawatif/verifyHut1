
<center>
  <!DOCTYPE html>
<html>
<style type="text/css">
.box{
  background-color: green;
  width: 500px;
  margin:120 auto;
  border: 5px solid #ccc;
  }


body{
  background-color: grey;
}
    /*assign full width inputs*/
    input[type=no_ic],
    input[type=password] {
        width: 50%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    /*set a style for the buttons*/
    button {
        background-color: #2F5F94 ;
        color: white;
        padding: 14px 20px;
        margin: 15px 0;
        border: none;
        cursor: pointer;
        width: 15%;
    }
    h2 {
      color: #154360;
      margin-left: 20px;
    }

.textbox i{
  width: 30px;
  float:left;
  text-align: center;
}

    /*style the model content box*/
    .modal-content {
        background-color: #fefefe;
        margin: 5% auto 15% auto;
        border: 1px solid #888;
        width: 80%;
    }
</style>

<div class="container box">
<center>
<h2>VERIFYHUT ADMIN SITE</h2>
    <form method="POST" action="login">
        {{ csrf_field() }}

        <br>
        <input type="no_ic" placeholder="Username" class="form-control" id="name" name="u"></br>
        <br>
        <input type="password" placeholder="Password" class="form-control" id="password" name="p"></br>

        <button style="cursor:pointer" type="submit" class="btn btn-primary" name="btn">Login</button>
		
		{!! Form::token() !!}
        </br>


      </form>
</div>
</center>
</body>
