<?php
    if(isset($_POST['email']) && isset($_POST['password'])){
        require_once "conn.php";
        $battery = $_POST['battery'];
        $email = $_POST['email'];
        $password = $_POST['password'];

        $sql = "update users set battery='$battery' where email='$email' and password='$password'";
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