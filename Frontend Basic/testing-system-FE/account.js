var accounts = []; // mảng chứa account
var v_idUpdate = -1;
var vTheme = "";
var baseUrl = "http://localhost:8080/accounts";
var baseUrlDepartment = "http://localhost:8080/departments";
var baseUrlPosition = "http://localhost:8080/positions";
var baseAvt = "https://avatars.githubusercontent.com/u/3143871";
var page = 0;
var totalPages = 0;

var loginInfor = localStorage.getItem("loginInfor");
if (loginInfor == null) {
    alert("Bạn chưa đăng nhập!");
    window.location.href = "login.html";
}

loadData();
loadDepartment();
loadPosition();
// load màu nên ở localStorage
vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

// document.getElementById('modal-id').addEventListener('hidden.bs.modal', function name() {

// })

function changeTheme(themeValue) {
    if (themeValue === "dark") {
        // thêm class .dark-theme vào body
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }
    localStorage.setItem("theme", themeValue);
}

function loadData() {
    // lấy ra các gtri cần tìm kiếm
    var usernameSearch = $("#usernameSearch").val();
    var fullNameSearch = $("#fullNameSearch").val();
    var emailSearch = $("#emailSearch").val();
    var departmentIdSearch = $("#departmentSearchID").val();
    var positionIdSearch = $("#positionSearchID").val();
    var size = $("#inputnumberOfRecordId").val();

    var subUrl = `?userName=${usernameSearch}&email=${emailSearch}&departmentId=${departmentIdSearch}&positionId=${positionIdSearch}&fullName=${fullNameSearch}&page=${page}&size=${size}&sort=id,desc`;
    // call api đến mockapi.io đe lấy ds account
    // jqAjax
    $.ajax({
        type: "GET",
        url: baseUrl + subUrl,
        // data: "data",  -- phục cho thêm hoặc update
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(`${loginInfor}`),
            );
        },
        success: function (response) {
            // call api thanh cong
            accounts = response.content;
            totalPages = response.totalPages;
            var tableContent = "";
            for (let i = 0; i < accounts.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + accounts[i].id + "</td>";
                tableContent +=
                    "<td><img src=" +
                    baseAvt +
                    " style='height: 50px' alt='Image' /></td>";
                tableContent += "<td>" + accounts[i].userName + "</td>";
                tableContent += "<td>" + accounts[i].fullName + "</td>";
                tableContent += "<td>" + accounts[i].email + "</td>";
                tableContent += "<td>" + accounts[i].departmentName + "</td>";
                tableContent += "<td>" + accounts[i].positionName + "</td>";
                tableContent +=
                    "<td><button class='btn btn-edit' onclick='onHandleEdit(" +
                    accounts[i].id +
                    ")'>Edit</button> " +
                    " <button class='btn btn-edit' onclick='onDelete(" +
                    accounts[i].id +
                    ")'>Delete</button></td>";
                tableContent += "</tr>";
            }
            // trước khi show data thì clear bảng trước
            //jqEmpty
            $("#tableBoby").empty();
            // jqAppend
            $("#tableBoby").append(tableContent);
            buildPagination();
        },
        error: function (error) {
            alert("Call api get account thất bại");
        },
    });
}

function buildPagination() {
    let html = "";

    // Previous
    if (page > 0) {
        html += `
            <li>
                <a href="#" onclick="changePage(${page - 1})">
                    &laquo;
                </a>
            </li>
        `;
    }

    // Các số trang
    for (let i = 0; i < totalPages; i++) {
        html += `
            <li class="${i === page ? "active" : ""}">
                <a href="#" onclick="changePage(${i})">
                    ${i + 1}
                </a>
            </li>
        `;
    }

    // Next
    if (page < totalPages - 1) {
        html += `
            <li>
                <a href="#" onclick="changePage(${page + 1})">
                    &raquo;
                </a>
            </li>
        `;
    }

    $("#pagination").html(html);
}

function changePage(newPage) {
    if (page === newPage) {
        return;
    }
    page = newPage;

    loadData();
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");
    if (check) {
        // dung ajax để call API xóa
        $.ajax({
            type: "DELETE",
            url: baseUrl + "/" + idDelete,
            // data: "data",
            // dataType: "dataType", dung cho GET
            beforeSend: function (xhr) {
                xhr.setRequestHeader(
                    "Authorization",
                    "Basic " + btoa(`${loginInfor}`),
                );
            },
            success: function (response) {
                alert("Xóa thành công!");
                loadData();
            },
            error: function (error) {
                alert("Call api xóa thất bại");
            },
        });
    }
}

function onCreate() {
    if (
        !validationField("usernameDangerId", "inputUsername", usernameRules) ||
        !validationField("fullNameDangerId", "inputFullname", fullnameRules) ||
        !validationField("emailDangerId", "inputEmail", emailRules)
    ) {
        return;
    }
    if (v_idUpdate > 0) {
        alert("Đang update, ko thể tạo mới dc");
        return;
    }
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();

    // đưa các dữ liệu trên vào object // object của js
    var account = {
        avatar: v_avatar,
        userName: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };
    //https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg
    // call api dể thêm mới account
    $.ajax({
        type: "POST",
        url: baseUrl,
        data: JSON.stringify(account), // chuyển account từ obejct của JS thành JSON
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(`${loginInfor}`),
            );
        },
        success: function (response) {
            alert("Thêm dữ liệu thành công");
            // hiển thị lại ds account
            loadData();
            // clear dữ lieu 3 ô username, fullName, age ở tren
            //jqValSet
            hideAndResetModal();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        },
    });
}

// jqSubmit
// $("#accountForm").submit(function (e) {
//     e.preventDefault();

// });

$("#submit").click(function (e) {
    // nếu v_idUpdate <= 0    thì sẽ tạo mới
    // nếu v_idUpdate > 0 thì sẽ update
    if (v_idUpdate <= 0) {
        onCreate();
    } else {
        onUpdate();
    }
});

function resetForm() {
    $(".modal-title").empty();
    $(".modal-title").append("<div>Create Account</div>");
    $("#inputAvatar").val("");
    $("#inputUsername").val("");
    $("#inputFullname").val("");
    $("#inputEmail").val("");
    $("#inputDepartmentName").prop("selectedIndex", 0);
    $("#inputPositionName").prop("selectedIndex", 0);
    v_idUpdate = -1;
}

function onHandleEdit(idUpdate) {
    // mo modal
    $("#modal-id").modal("show");
    // call api get by id đẻ lấy lấy dữ liệu ra để hiển thị lên các ô input
    $.ajax({
        type: "GET",
        url: baseUrl + "/" + idUpdate,
        // data: "data",
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(`${loginInfor}`),
            );
        },
        success: function (response) {
            $(".modal-title").empty();
            $(".modal-title").append("<div>Update Account</div>");
            // hien thi ra cac o input tuong ung
            $("#inputAvatar").val(response.avatar);
            $("#inputUsername").val(response.userName);
            $("#inputFullname").val(response.fullName);
            $("#inputEmail").val(response.email);
            $("#inputDepartmentName").val(response.departmentId);
            $("#inputPositionName").val(response.positionId);

            v_idUpdate = idUpdate; // lưu lại id cần update
        },
        error: function (error) {
            alert(error.responseJSON.message);
        },
    });
}

function onUpdate() {
    if (
        !validationField("usernameDangerId", "inputUsername", usernameRules) ||
        !validationField("fullNameDangerId", "inputFullname", fullnameRules) ||
        !validationField("emailDangerId", "inputEmail", emailRules)
    ) {
        return;
    } else {
        var v_avatar = $("#inputAvatar").val();
        var v_username = $("#inputUsername").val();
        var v_fullName = $("#inputFullname").val();
        var v_email = $("#inputEmail").val();
        var v_departmentID = $("#inputDepartmentName").val();
        var v_positionID = $("#inputPositionName").val();

        // lay ra doi tuong can update
        var accountUpdate = {
            avatar: v_avatar,
            userName: v_username,
            fullName: v_fullName,
            email: v_email,
            departmentId: v_departmentID,
            positionId: v_positionID,
        };
        // call api update
        $.ajax({
            type: "PUT",
            url: baseUrl + "/" + v_idUpdate,
            data: JSON.stringify(accountUpdate),
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(
                    "Authorization",
                    "Basic " + btoa(`${loginInfor}`),
                );
            },
            success: function (response) {
                alert("Update dữ liệu thành công");
                // hiển thi ls account
                loadData();
                //jqValSet
                hideAndResetModal();
            },
            error: function (error) {
                alert(error.responseJSON.message);
            },
        });
    }
}

function loadDepartment() {
    $.ajax({
        type: "GET",
        url: baseUrlDepartment,
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(`${loginInfor}`),
            );
        },

        success: function (response) {
            var content = "";

            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">
                        ${response[i].name}
                    </option>`;
            }

            $("#inputDepartmentName").empty();
            $("#inputDepartmentName").append(content);
            // load cho ô tìm kiếm
            $("#departmentSearchID").empty();
            $("#departmentSearchID").append("<option value=''>Tất cả</option>");
            $("#departmentSearchID").append(content);
        },
        error: function (error) {
            alert("Call api get department thất bại");
        },
    });
}

function loadPosition() {
    $.ajax({
        type: "GET",
        url: baseUrlPosition,
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(
                "Authorization",
                "Basic " + btoa(`${loginInfor}`),
            );
        },
        success: function (response) {
            var content = "";

            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">
                        ${response[i].name}
                    </option>`;
            }

            $("#inputPositionName").empty();
            $("#inputPositionName").append(content);
            // load cho ô tìm kiếm
            $("#positionSearchID").empty();
            $("#positionSearchID").append("<option value=''>Tất cả</option>");
            $("#positionSearchID").append(content);
        },
        error: function (error) {
            alert("Call api get department thất bại");
        },
    });
}

// function validationUsername() {
//     $("#usernameDangerId").empty();
//     var v_username = $("#inputUsername").val();
//     if (v_username.trim() == "") {
//         //v_username !=null
//         $("#usernameDangerId").append("Username không được để trống");
//         return false;
//     }
//     if (v_username.length > 100) {
//         $("#usernameDangerId").append("Username không dài quá một 100 kí tự");
//         return false;
//     }
//     return true;
// }
// function validationFullname() {
//     $("#fullNameDangerId").empty();
//     var v_fullname = $("#inputFullname").val();
//     if (v_fullname.trim() == "") {
//         //v_username !=null
//         $("#fullNameDangerId").append("Fullname không được để trống");
//         return false;
//     }
//     if (v_fullname.length > 100) {
//         $("#fullNameDangerId").append("Fullname không dài quá một 100 kí tự");
//         return false;
//     }
//     return true;
// }
// function validationEmail() {
//     $("#emailDangerId").empty();
//     const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     var v_email = $("#inputEmail").val();
//     if (v_email.trim() == "") {
//         //v_username !=null
//         $("#emailDangerId").append("Email không được để trống");
//         return false;
//     }
//     if (!regex.test(v_email)) {
//         //v_username !=null
//         $("#emailDangerId").append("Email không đúng định dạng");
//         return false;
//     }
//     if (v_email.length > 100) {
//         $("#emailDangerId").append("Email không dài quá một 100 kí tự");
//         return false;
//     }
//     return true;
// }

var emailRules = {
    name: "Email",
    required: true,
    length: 100,
    pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
};

var usernameRules = {
    name: "UserName",
    required: true,
    length: 100,
};
var fullnameRules = {
    name: "Fullname",
    required: true,
    length: 100,
};

function validationField(errorId, inputId, rules) {
    //clear ô show lỗi
    $(`#${errorId}`).empty();
    //lấy ra giá trị của ô cần validation
    var inputValue = $(`#${inputId}`).val();
    if (rules.required && inputValue.trim() == "") {
        $(`#${errorId}`).append(`${rules.name} không được để trống`);
        return false;
    }
    if (rules.length && inputValue.length > rules.length) {
        $(`#${errorId}`).append(
            `${rules.name} không được dài quá ${rules.length} kí tự`,
        );
        return false;
    }
    if (rules.pattern && !rules.pattern.test(inputValue)) {
        $(`#${errorId}`).append(`${rules.name} không đúng định dạng`);
        return false;
    }
    return true;
}

function hideAndResetModal() {
    resetForm();
    $("#usernameDangerId").empty();
    $("#fullNameDangerId").empty();
    $("#emailDangerId").empty();
    $("#modal-id").modal("hide");
}
$("#modal-id").on("hidden.bs.modal", function () {
    resetForm();

    $("#usernameDangerId").empty();
    $("#fullNameDangerId").empty();
    $("#emailDangerId").empty();
});
function logout() {
    // Xóa thông tin đăng nhập
    localStorage.removeItem("loginInfor");

    // Chuyển về trang login
    window.location.href = "login.html";
}