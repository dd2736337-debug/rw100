const ORDER_URL = "http://localhost:8080/api/orders";

loadOrders();

function loadOrders() {
    $.ajax({
        url: ORDER_URL,
        type: "GET",

        success: function (response) {
            let html = "";

            response.forEach((order) => {
                html += `
                    <tr>

                        <td>${order.id}</td>

                        <td>
                            ${order.user.fullName}
                        </td>

                        <td>
                            ${order.totalPrice}
                        </td>

                        <td>
                            ${order.status}
                        </td>

                        <td>

                            <button
                                    class="btn btn-warning"
                                    onclick="changeStatus(${order.id})">
                                Đổi trạng thái
                            </button>

                            <button
                                    class="btn btn-info"
                                    onclick="viewDetail(${order.id})">
                                Chi tiết
                            </button>

                        </td>

                    </tr>
                `;
            });

            $("#orderTable").html(html);
        },
    });
}

function changeStatus(id) {
    $("#orderId").val(id);

    let modal = new bootstrap.Modal(document.getElementById("statusModal"));

    modal.show();
}

function updateStatus() {
    let id = $("#orderId").val();

    $.ajax({
        url: ORDER_URL + "/" + id + "/status",
        type: "PUT",
        contentType: "application/json",

        data: JSON.stringify({
            status: $("#status").val(),
        }),

        success: function () {
            alert("Cập nhật thành công!");

            location.reload();
        },
    });
}

function viewDetail(id) {
    window.location.href = "order-detail.html?id=" + id;
}
