var accounts = []; // mảng chứa account
var v_idUpdate = -1;
var vTheme = '';

loadData();
// load màu nên ở localStorage
vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

// document.getElementById('modal-id').addEventListener('hidden.bs.modal', function name() {
    
// }) 

function changeTheme(themeValue) {
    if(themeValue === 'dark') {
        // thêm class .dark-theme vào body
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }
    localStorage.setItem('theme', themeValue)
}


function loadData() {
    // call api đến mockapi.io đe lấy ds account
    // jqAjax
    $.ajax({
        type: "GET",
        url: "https://6a3df0780443193a1a0b390e.mockapi.io/api/v1/account",
        // data: "data",  -- phục cho thêm hoặc update
        dataType: "JSON",
        success: function (response) {
            // call api thanh cong
            accounts = response;
            var tableContent = "";
            for (let i = 0; i < accounts.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + accounts[i].id + "</td>";
                tableContent += "<td><img src=" + accounts[i].avatar + " style='height: 50px' alt='Image' /></td>";
                tableContent += "<td>" + accounts[i].username + "</td>";
                tableContent += "<td>" + accounts[i].fullName + "</td>";
                tableContent += "<td>" + accounts[i].age + "</td>";
                tableContent +=
                    "<td><button onclick='onHandleEdit(" +
                    accounts[i].id +
                    ")'>Edit</button> " +
                    " <button onclick='onDelete(" +
                    accounts[i].id +
                    ")'>Delete</button></td>";
                tableContent += "</tr>";
            }
            // trước khi show data thì clear bảng trước
            //jqEmpty
            $("#tableBoby").empty();
            // jqAppend
            $("#tableBoby").append(tableContent);
        },
        error: function (error) {
            alert("Call api get account thất bại");
        },
    });
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");
    if (check) {
        // dung ajax để call API xóa
        $.ajax({
            type: "DELETE",
            url: "https://6a3df0780443193a1a0b390e.mockapi.io/api/v1/account/" + idDelete,
            // data: "data",
            // dataType: "dataType", dung cho GET
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

function onCreate(idDelete) {
    if (v_idUpdate > 0) {
        alert("Đang update, ko thể tạo mới dc");
        return;
    }
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_age = $("#inputAge").val();

    // đưa các dữ liệu trên vào object // object của js
    var account = {
        avatar: v_avatar,
        username: v_username,
        fullName: v_fullName,
        age: v_age,
    };
    //https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg
    // call api dể thêm mới account
    $.ajax({
        type: "POST",
        url: "https://6a3df0780443193a1a0b390e.mockapi.io/api/v1/account",
        data: JSON.stringify(account), // chuyển account từ obejct của JS thành JSON
        contentType: "application/json",
        success: function (response) {
            alert("Thêm dữ liệu thành công");
            // hiển thị lại ds account
            loadData();
            // clear dữ lieu 3 ô username, fullName, age ở tren
            //jqValSet
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputAge").val("");
            $('#modal-id').modal('hide');
        },
        error: function (error) {
            alert("Call api thêm mới thất bại");
        },
    });
};

// jqSubmit
// $("#accountForm").submit(function (e) {
//     e.preventDefault();
    
// });

$("#submit").click(function (e) { 
    // nếu v_idUpdate <= 0    thì sẽ tạo mới
    // nếu v_idUpdate > 0 thì sẽ update
    if (v_idUpdate <=0) {
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
    $("#inputAge").val("");
    v_idUpdate = -1;
}

function onHandleEdit(idUpdate) {
    // mo modal 
    $('#modal-id').modal('show');
    // call api get by id đẻ lấy lấy dữ liệu ra để hiển thị lên các ô input
    $.ajax({
        type: "GET",
        url: "https://6a3df0780443193a1a0b390e.mockapi.io/api/v1/account/" + idUpdate,
        // data: "data",
        dataType: "JSON",
        success: function (response) {
            $(".modal-title").empty();
            $(".modal-title").append("<div>Update Account</div>");
            // hien thi ra cac o input tuong ung
            $("#inputAvatar").val(response.avatar);
            $("#inputUsername").val(response.username);
            $("#inputFullname").val(response.fullName);
            $("#inputAge").val(response.age);
            v_idUpdate = idUpdate; // lưu lại id cần update
        },
        error: function (error) {
            alert("Call api lấy thông tin thất bại");
        },
    });
}

function onUpdate(idDelete) {
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_age = $("#inputAge").val();
    // lay ra doi tuong can update
    var accountUpdate = {
        avatar: v_avatar,
        username: v_username,
        fullName: v_fullName,
        age: v_age,
    };
    // call api update
    $.ajax({
        type: "PUT",
        url: "https://6a3df0780443193a1a0b390e.mockapi.io/api/v1/account/" + v_idUpdate,
        data: JSON.stringify(accountUpdate),
        contentType: "application/json",
        success: function (response) {
            alert("Update dữ liệu thành công");
            // hiển thi ls account
            loadData();
            //jqValSet
            v_idUpdate = -1;
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputAge").val("");
            $('#modal-id').modal('hide');
        },
        error: function (error) {
            alert("Call api update thất bại");
        },
    });
};