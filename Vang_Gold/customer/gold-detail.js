const urlParams = new URLSearchParams(window.location.search);

const goldId = urlParams.get("id");

const GOLD_URL = "http://localhost:8080/api/golds/";

loadGold();

function loadGold() {
    $.ajax({
        url: GOLD_URL + goldId,
        type: "GET",

        success: function (gold) {
            $("#name").text(gold.name);
            $("#type").text(gold.type);
            $("#weight").text(gold.weight);
            $("#price").text(gold.price);
            $("#category").text(gold.category.name);
        },
    });
}

function buyNow() {
    let order = {
        totalPrice: $("#price").text() * $("#quantity").val(),

        status: "PENDING",

        user: {
            id: localStorage.getItem("userId"),
        },

        orderDetails: [
            {
                quantity: $("#quantity").val(),

                gold: {
                    id: goldId,
                },
            },
        ],
    };

    $.ajax({
        url: "http://localhost:8080/api/orders",

        type: "POST",

        contentType: "application/json",

        data: JSON.stringify(order),

        success: function () {
            alert("Đặt hàng thành công!");

            window.location.href = "my-orders.html";
        },
    });
}
