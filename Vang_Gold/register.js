const REGISTER_URL = "http://localhost:8080/api/users/register";

function register() {
    let user = {
        username: $("#username").val(),
        password: $("#password").val(),
        fullName: $("#fullName").val(),
        email: $("#email").val(),
        phone: $("#phone").val(),
        address: $("#address").val(),
        role: "CUSTOMER",
    };

    $.ajax({
        url: REGISTER_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),

        success: function () {
            alert("Đăng ký thành công!");

            window.location.href = "login.html";
        },

        error: function () {
            alert("Đăng ký thất bại!");
        },
    });
}
