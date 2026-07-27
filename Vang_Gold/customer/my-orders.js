const USER_ID = localStorage.getItem("userId");

const ORDER_URL = "http://localhost:8080/api/orders/user/" + USER_ID;

loadMyOrders();

function loadMyOrders() {
    $.ajax({
        url: ORDER_URL,
        type: "GET",

        success: function (response) {
            let html = "";

            response.forEach((order) => {
                html += `
                    <tr>

                        <td>
                            ${order.id}
                        </td>

                        <td>
                            ${order.totalPrice}
                        </td>

                        <td>
                            ${order.status}
                        </td>

                        <td>

                            <button
                                class="btn btn-info"
                                onclick="viewOrder(
                                    ${order.id}
                                )">

                                Xem

                            </button>

                        </td>

                    </tr>
                `;
            });

            $("#orderTable").html(html);
        },
    });
}

function viewOrder(id) {
    alert("Chi tiết đơn hàng ID: " + id);

    // Có thể tạo thêm:
    // order-detail.html?id=1
}
