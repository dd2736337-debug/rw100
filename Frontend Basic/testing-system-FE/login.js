function login() {
    var username = $("#inputUsername").val();
    var password = $("#inputPassword").val();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/auth/login",
        dataType: "JSON",

        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(username + ":" + password),
            );
        },

        success: function (response) {
            alert("Đăng nhập thành công");
            localStorage.setItem("loginInfor",username+":"+password);
            window.location.href = "account.html";
        },

        error: function (error) {
            console.log(error);
            alert("Sai thông tin đăng nhập");
        },
    });
}
