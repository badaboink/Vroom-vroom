<?php
    if(isset($_POST['email']) && isset($_POST['password'])){
        require_once "conn.php";
        $car = $_POST['connected_auto'];
        $carnr = $_POST['auto_nr'];
        $email = $_POST['email'];
        $password = $_POST['password'];

        $sql = "update users set auto_nr='$carnr', connected_auto='$car' where email='$email' and password='$password'";
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