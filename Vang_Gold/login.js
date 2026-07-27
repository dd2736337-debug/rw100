const LOGIN_URL = "http://localhost:8080/api/auth/login";

function login() {
    let user = {
        username: $("#username").val(),
        password: $("#password").val(),
    };

    $.ajax({
        url: LOGIN_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),

        success: function (response) {
            localStorage.setItem("token", response.token);

            localStorage.setItem("role", response.role);

            localStorage.setItem("userId", response.userId);

            if (response.role === "ADMIN") {
                window.location.href = "./admin/dashboard.html";
            } else {
                window.location.href = "./customer/home.html";
            }
        },

        error: function () {
            alert("Sai tài khoản hoặc mật khẩu!");
        },
    });
}
