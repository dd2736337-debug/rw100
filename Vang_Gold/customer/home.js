const BASE_URL = "http://localhost:8080/api/golds";

loadGolds();

function loadGolds() {
    $.ajax({
        url: BASE_URL,
        type: "GET",

        success: function (response) {
            let html = "";

            response.content.forEach((gold) => {
                html += `

                    <div class="col-md-4">

                        <div class="card shadow mb-4">

                            <div class="card-body">

                                <h4>${gold.name}</h4>

                                <p>
                                    Loại:
                                    ${gold.type}
                                </p>

                                <p>
                                    Khối lượng:
                                    ${gold.weight}
                                </p>

                                <p>
                                    Giá:
                                    ${gold.price}
                                </p>

                                <button
                                    class="btn btn-primary"
                                    onclick="viewDetail(${gold.id})">

                                    Chi tiết

                                </button>

                            </div>

                        </div>

                    </div>
                `;
            });

            $("#goldList").html(html);
        },
    });
}

function viewDetail(id) {
    window.location.href = "gold-detail.html?id=" + id;
}

function logout() {
    localStorage.clear();

    window.location.href = "../login.html";
}
