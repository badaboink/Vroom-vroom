<?php
    if(isset($_POST['email']) && isset($_POST['password'])){
        require_once "conn.php";
        $email = $_POST['email'];
        $password = $_POST['password'];

        $sql = "update users set password='$password' where email='$email'";
        $result = $conn->query($sql) or die(mysql_error());
        if($result == true)
        {
            echo "true";
        }
        else
        {
            echo "failure";
        }
        mysqli_close($conn);
    }

?>