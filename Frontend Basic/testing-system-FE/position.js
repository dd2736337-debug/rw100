let positions = [];
let baseUrl = "http://localhost:8080/positions";
let v_idUpdate = -1;

//================= Load =================

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

//================= Load Data =================

function loadData() {

    $.ajax({

        type: "GET",

        url: baseUrl,

        dataType: "json",

        success: function (response) {

            positions = response;

            let tableContent = "";

            for (let i = 0; i < positions.length; i++) {

                tableContent += `
                    <tr>

                        <td>${positions[i].id}</td>

                        <td>${positions[i].name}</td>

                        <td>

                            <button
                                class="btn btn-warning btn-xs"
                                onclick="editPosition(${positions[i].id})">

                                Edit

                            </button>

                            <button
                                class="btn btn-danger btn-xs"
                                onclick="onDelete(${positions[i].id})">

                                Delete

                            </button>

                        </td>

                    </tr>
                `;

            }

            $("#tableBody").html(tableContent);

        },

        error: function () {

            alert("Không lấy được dữ liệu");

        }

    });

}

//================= Reset =================

function resetForm() {

    v_idUpdate = -1;

    $("#inputPositionName").val("");

    $(".modal-title").text("Create Position");

}

//================= Create =================

function onCreate() {

    let position = {

        name: $("#inputPositionName").val()

    };

    if (position.name == "") {

        alert("Vui lòng chọn Position");

        return;

    }

    $.ajax({

        type: "POST",

        url: baseUrl,

        data: JSON.stringify(position),

        contentType: "application/json",

        success: function () {

            alert("Thêm thành công");

            $("#modal-id").modal("hide");

            loadData();

            resetForm();

        },

        error: function (xhr) {

            alert(xhr.responseText);

        }

    });

}

//================= Edit =================

function editPosition(id) {

    v_idUpdate = id;

    $(".modal-title").text("Update Position");

    $("#modal-id").modal("show");

    $.ajax({

        type: "GET",

        url: baseUrl + "/" + id,

        success: function (response) {

            $("#inputPositionName").val(response.name);

        },

        error: function () {

            alert("Không lấy được dữ liệu");

        }

    });

}

//================= Update =================

function onUpdate() {

    let position = {

        name: $("#inputPositionName").val()

    };

    $.ajax({

        type: "PUT",

        url: baseUrl + "/" + v_idUpdate,

        data: JSON.stringify(position),

        contentType: "application/json",

        success: function () {

            alert("Cập nhật thành công");

            $("#modal-id").modal("hide");

            loadData();

            resetForm();

        },

        error: function (xhr) {

            alert(xhr.responseText);

        }

    });

}

//================= Delete =================

function onDelete(id) {

    let check = confirm("Bạn có chắc muốn xóa Position?");

    if (!check) {

        return;

    }

    $.ajax({

        type: "DELETE",

        url: baseUrl + "/" + id,

        success: function () {

            alert("Xóa thành công");

            loadData();

        },

        error: function (xhr) {

            alert(xhr.responseText);

        }

    });

}