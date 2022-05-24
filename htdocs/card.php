<?php
    if(isset($_POST['email']) && isset($_POST['password'] && isset($_POST['card_info'])){
        require_once "conn.php";
        $card = $_POST['card_info'];
        $email = $_POST['email'];
        $password = $_POST['password'];

        $sql = "update users set card_info='$card' where email='$email' and password='$password'";
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