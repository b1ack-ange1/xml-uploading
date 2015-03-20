<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>XML upload</title>
        <style>
            body{
                background: skyblue;
                width: 100%;
                height: 100%;
            }
            .container-fluid{
                position: absolute;
                top: 0px;
                bottom: 0px;
                left: 0px;
                right: 0px;
                width: 410px;
                height: 300px;
                margin: auto;
                background:  aquamarine;
                border: 2px solid black;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }
            .control-group{
                margin-bottom: 20px;
            }
            button{
                border: 2px solid black;
                border-radius: 5px;
                background: cyan;
                color: black;
                padding: 5px;
                font-weight: bolder;
            }
            button:hover{
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }
            .file_upload {
                position: relative;
                overflow: hidden;
                background: #eee;
                border: 1px solid #ccc;
                font-size: 30px;
                line-height: 1;
                text-align: center;
                padding: 20px;
                width: 40px;
                margin-bottom: 20px;
            }

            .file_upload input[type=file] {
                position: absolute;
                top: 0; right: 0;
                font-size: 200px;
                opacity: 0;
                filter: alpha(opacity=0);
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <fieldset>
                <legend>XML upload application</legend>
                <form method="post" action='fileupload/upload.htm' enctype="multipart/form-data">
                    <label>Upload file</label>

                    <div class="file_upload">+<input type="file" name="file1" id="file1" value=""></div>

                    <button type="submit">Upload File</button>
                </form>
            </fieldset>
        </div>
    </body>
</html>