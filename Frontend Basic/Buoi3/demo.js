var v_id = 4;
var v_idUpdate = -1;
var arrays = [
    {
        id: 1,
        username: "Nguyễn",
        fullName: "Nguyễn Văn A",
        age: 20,
    },

    {
        id: 2,
        username: "Trần",
        fullName: "Trần Văn B",
        age: 30,
    },

    {
        id: 3,
        username: "Ngô",
        fullName: "Ngô Thị C",
        age: 25,
    },

    {
        id: 4,
        username: "Lê",
        fullName: "Lê Văn D",
        age: 21,
    },
];

// <tr>
//                 <td>1</td>
//                 <td>Username1</td>
//                 <td>FullName1</td>
//                 <td>11</td>
//                 <td>
//                     <button>Update</button>
//                     <button>Delete</button>
//                 </td>
//             </tr>

loadData();

function loadData() {
    var tableContent = "";
    for (let i = 0; i < arrays.length; i++) {
        tableContent += "<tr>";
        tableContent += "<td>" + arrays[i].id + "</td>";
        tableContent += "<td>" + arrays[i].username + "</td>";
        tableContent += "<td>" + arrays[i].fullName + "</td>";
        tableContent += "<td>" + arrays[i].age + "</td>";
        tableContent +=
            "<td><button onclick='onHandleUpdate(" +
            arrays[i].id +
            ")'>Edit</button> <button onclick='onDelete(" +
            arrays[i].id +
            ")'>Delete</button></td>";
        tableContent += "</tr>";
    }
    // document.getElementById("tableBody").innerHTML = tableContent; //nối dữ liệu vào table body có id=tableBody
    //Trước khi show data thì clear bảng trước
    //jqEmpty
    $("#tableBody").empty();

    //cú pháp JqAppend
    $("#tableBody").append(tableContent);
}

function onDelete(idDelete) {
    var comfirm = confirm("Bạn có chắc chắn xóa account này không?");
    if (comfirm == true) {
        var indexDelete = -1;
        for (let i = 0; i < arrays.length; i++) {
            if (arrays[i].id == idDelete) {
                indexDelete = i;

                break;
            }
        }
        arrays.splice(indexDelete, 1); //Xóa phần tử theo vị trí,số lượng phần tử muốn xóa
        alert("Xóa thành công");
        loadData(); //Hiển thị lại danh sách
    }
}

//JqSubmit
$("#AccountForm").submit(function (e) {
    e.preventDefault();
    if (v_idUpdate > 0) {
        alert("Đang update ,không thể tạo mới được");
        return;
    }

    var v_username = $("#inputUsername").val();
    var v_fullname = $("#inputFullname").val();
    var v_age = $("#inputAge").val();

    var account = {
        id: ++v_id,
        username: v_username,
        fullName: v_fullname,
        age: v_age,
    };

    arrays.push(account);
    alert("Thêm dữ liệu thành công");
    loadData();

    $("#inputUsername").val("");
    $("#inputFullname").val("");
    $("#inputAge").val("");
});

// function onCreate() {
//     //lấy dữ liệu từ 3 ô username,fullname,age ở trên
//     var v_username = $("#inputUsername").val();
//     var v_fullname = $("#inputFullname").val();
//     var v_age = $("#inputAge").val();
//     // console.log(v_username + "," + v_fullname + " ," + v_age);
//     //đưa các dữ liệu trên vào object và thêm vào arrays
//     var account = {
//         id: ++v_id,
//         username: v_username,
//         fullName: v_fullname,
//         age: v_age,
//     };
//     arrays.push(account);
//     //hiển thị lại ds account
//     loadData();
//     //clear dữ liệu từ 3 ô username,fullname,age ở trên
//     // document.getElementById("inputUsername").value = "";
//     // document.getElementById("inputFullname").value = "";
//     // document.getElementById("inputAge").value = "";
//     $("#inputUsername").val("");
//     $("#inputFullname").val("");
//     $("#inputAge").val("");
// }

function onHandleUpdate(idUpdate) {
    //dựa vào id để show dữ liệu lên các ô input
    for (let i = 0; i < arrays.length; i++) {
        if (arrays[i].id == idUpdate) {
            $("#inputUsername").val(arrays[i].username);
            $("#inputFullname").val(arrays[i].fullName);
            $("#inputAge").val(arrays[i].age);
            v_idUpdate = idUpdate; //Lưu lại id cần update
            break;
        }
    }
    // //disabled nut create đi
    // $("#btnCreate").prop("disabled", true);
}

$("#btnUpdate").click(function (e) {
    var v_username = $("#inputUsername").val();
    var v_fullname = $("#inputFullname").val();
    var v_age = $("#inputAge").val();
    //tìm vị trí của object cần update trong mảng
    var v_indexUpdate = -1;
    // for (let i = 0; i < arrays.length; i++) {
    //     if (arrays[i].id == v_idUpdate) {
    //         v_indexUpdate = i;
    //         break;
    //     }
    // }

    v_indexUpdate = arrays.findIndex((i) => i.id == v_idUpdate);

    //update giá trị  ở vị trí thứ i =giá trị nhập từ màn hình
    if (v_idUpdate == -1) {
        alert("Phần tử này không tồn tại hoặc đang tạo mới");
    } else {
        arrays[v_indexUpdate] = {
            id: v_idUpdate,
            username: v_username,
            fullName: v_fullname,
            age: v_age,
        };
        alert("Update dữ liệu thành công");
        //hiển thị lại danh sách account
        loadData();
        v_idUpdate = -1;
        $("#inputUsername").val("");
        $("#inputFullname").val("");
        $("#inputAge").val("");
    }
});

// function onUpdateAccount() {
//     var v_username = $("#inputUsername").val();
//     var v_fullname = $("#inputFullname").val();
//     var v_age = $("#inputAge").val();
//     //tìm vị trí của object cần update trong mảng
//     var v_indexUpdate = -1;
//     // for (let i = 0; i < arrays.length; i++) {
//     //     if (arrays[i].id == v_idUpdate) {
//     //         v_indexUpdate = i;
//     //         break;
//     //     }
//     // }

//     v_indexUpdate = arrays.findIndex((i) => i.id == v_idUpdate);

//     //update giá trị  ở vị trí thứ i =giá trị nhập từ màn hình
//     if (v_idUpdate == -1) {
//         alert("Phần tử này không tồn tại hoặc đang tạo mới");
//     } else {
//         arrays[v_indexUpdate] = {
//             id: v_idUpdate,
//             username: v_username,
//             fullName: v_fullname,
//             age: v_age,
//         };
//         alert("Update dữ liệu thành công");
//         //hiển thị lại danh sách account
//         loadData();
//         v_idUpdate = -1;
//         $("#inputUsername").val("");
//         $("#inputFullname").val("");
//         $("#inputAge").val("");
//     }
// }
