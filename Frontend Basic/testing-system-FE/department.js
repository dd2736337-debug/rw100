let departments = [];
let baseUrl = "http://localhost:8080/departments";
let v_idUpdate = -1;

//================== Theme ==================

let vTheme = localStorage.getItem("theme") || "light";
changeTheme(vTheme);

function changeTheme(themeValue) {
    if (themeValue === "dark") {
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }

    localStorage.setItem("theme", themeValue);
}

//================== Load ==================

$(document).ready(function () {
    loadData();

    $("#submit").click(function () {
        if (v_idUpdate == -1) {
            onCreate();
        } else {
            onUpdate();
        }
    });
});

function loadData() {

    $.ajax({
        type: "GET",
        url: baseUrl,
        dataType: "json",

        success: function (response) {

            departments = response;

            let tableContent = "";

            for (let i = 0; i < departments.length; i++) {

                tableContent += `
                    <tr>
                        <td>${departments[i].id}</td>
                        <td>${departments[i].name}</td>

                        <td>

                            <button
                                class="btn btn-warning btn-xs"
                                onclick="editDepartment(${departments[i].id})">
                                Edit
                            </button>

                            <button
                                class="btn btn-danger btn-xs"
                                onclick="onDelete(${departments[i].id})">
                                Delete
                            </button>

                        </td>
                    </tr>
                `;
            }

            $("#tableBody").html(tableContent);

        },

        error: function () {
            alert("Call API thất bại");
        }

    });

}

//================== Reset ==================

function resetForm() {

    v_idUpdate = -1;

    $("#inputDepartmentName").val("");

    $(".modal-title").text("Create Department");

}

//================== Create ==================

function onCreate() {

    let v_name = $("#inputDepartmentName").val().trim();

    if (v_name == "") {
        alert("Tên Department không được để trống");
        return;
    }

    let department = {
        name: v_name
    };

    $.ajax({

        type: "POST",

        url: baseUrl,

        data: JSON.stringify(department),

        contentType: "application/json",

        success: function () {

            alert("Thêm thành công");

            $("#modal-id").modal("hide");

            $("#inputDepartmentName").val("");

            loadData();

        },

        error: function () {

            alert("Thêm thất bại");

        }

    });

}

//================== Edit ==================

function editDepartment(id) {

    v_idUpdate = id;

    $(".modal-title").text("Update Department");

    $("#modal-id").modal("show");

    $.ajax({

        type: "GET",

        url: baseUrl + "/" + id,

        dataType: "json",

        success: function (response) {

            $("#inputDepartmentName").val(response.name);

        },

        error: function () {

            alert("Không lấy được dữ liệu");

        }

    });

}

//================== Update ==================

function onUpdate() {

    let v_name = $("#inputDepartmentName").val().trim();

    if (v_name == "") {
        alert("Tên Department không được để trống");
        return;
    }

    let department = {
        name: v_name
    };

    $.ajax({

        type: "PUT",

        url: baseUrl + "/" + v_idUpdate,

        data: JSON.stringify(department),

        contentType: "application/json",

        success: function () {

            alert("Update thành công");

            $("#modal-id").modal("hide");

            $("#inputDepartmentName").val("");

            v_idUpdate = -1;

            loadData();

        },

        error: function () {

            alert("Update thất bại");

        }

    });

}

//================== Delete ==================

function onDelete(idDelete) {

    let check = confirm("Bạn có chắc chắn muốn xóa Department này?");

    if (!check) {
        return;
    }

    $.ajax({

        type: "DELETE",

        url: baseUrl + "/" + idDelete,

        success: function () {

            alert("Xóa thành công");

            loadData();

        },

        error: function () {

            alert("Xóa thất bại");

        }

    });

}