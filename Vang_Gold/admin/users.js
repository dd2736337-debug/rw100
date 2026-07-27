const USER_URL = "http://localhost:8080/api/users";

loadUsers();

function loadUsers() {
    $.ajax({
        url: USER_URL,
        type: "GET",

        success: function (response) {
            let html = "";

            response.forEach((user) => {
                html += `
                    <tr>

                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.fullName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.role}</td>

                        <td>

                            <button
                                    class="btn btn-warning"
                                    onclick="editUser(${user.id})">
                                Sửa
                            </button>

                            <button
                                    class="btn btn-danger"
                                    onclick="deleteUser(${user.id})">
                                Xóa
                            </button>

                        </td>

                    </tr>
                `;
            });

            $("#userTable").html(html);
        },
    });
}

function saveUser() {
    let user = {
        username: $("#username").val(),
        password: $("#password").val(),
        fullName: $("#fullName").val(),
        email: $("#email").val(),
        phone: $("#phone").val(),
        address: $("#address").val(),
        role: $("#role").val(),
    };

    $.ajax({
        url: USER_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),

        success: function () {
            alert("Thêm User thành công!");

            location.reload();
        },
    });
}

function deleteUser(id) {
    if (!confirm("Bạn có chắc muốn xóa User?")) {
        return;
    }

    $.ajax({
        url: USER_URL + "/" + id,
        type: "DELETE",

        success: function () {
            alert("Xóa thành công!");

            loadUsers();
        },
    });
}

function editUser(id) {
    $.ajax({
        url: USER_URL + "/" + id,
        type: "GET",

        success: function (user) {
            $("#id").val(user.id);
            $("#username").val(user.username);
            $("#fullName").val(user.fullName);
            $("#email").val(user.email);
            $("#phone").val(user.phone);
            $("#address").val(user.address);
            $("#role").val(user.role);

            let modal = new bootstrap.Modal(
                document.getElementById("userModal"),
            );

            modal.show();
        },
    });
}
