File: admin / js / golds.js;

let page = 0;

const BASE_URL = "http://localhost:8080/api/golds";

loadGolds();

function loadGolds() {
    $.ajax({
        url: BASE_URL + "?page=" + page + "&size=5",
        type: "GET",

        success: function (response) {
            let html = "";

            response.content.forEach((gold) => {
                html += `
                    <tr>
                        <td>${gold.id}</td>
                        <td>${gold.name}</td>
                        <td>${gold.type}</td>
                        <td>${gold.weight}</td>
                        <td>${gold.price}</td>
                        <td>${gold.quantity}</td>
                        <td>${gold.category.name}</td>

                        <td>
                            <button
                                class="btn btn-warning"
                                onclick="editGold(${gold.id})">
                                Sửa
                            </button>

                            <button
                                class="btn btn-danger"
                                onclick="deleteGold(${gold.id})">
                                Xóa
                            </button>
                        </td>
                    </tr>
                `;
            });

            $("#goldTable").html(html);
        },
    });
}

function saveGold() {
    let gold = {
        name: $("#name").val(),
        type: $("#type").val(),
        weight: $("#weight").val(),
        price: $("#price").val(),
        quantity: $("#quantity").val(),

        category: {
            id: $("#categoryId").val(),
        },
    };

    $.ajax({
        url: BASE_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(gold),

        success: function () {
            alert("Thêm thành công");

            location.reload();
        },
    });
}

function deleteGold(id) {
    if (!confirm("Bạn có chắc muốn xóa?")) {
        return;
    }

    $.ajax({
        url: BASE_URL + "/" + id,
        type: "DELETE",

        success: function () {
            alert("Xóa thành công");

            loadGolds();
        },
    });
}
