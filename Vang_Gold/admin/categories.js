
const CATEGORY_URL = "http://localhost:8080/api/categories";

loadCategories();

function loadCategories() {

    $.ajax({

        url: CATEGORY_URL,
        type: "GET",

        success: function (response) {

            let html = "";

            response.forEach(category => {

                html += `
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>

                        <td>

                            <button
                                    class="btn btn-warning"
                                    onclick="editCategory(${category.id})">
                                Sửa
                            </button>

                            <button
                                    class="btn btn-danger"
                                    onclick="deleteCategory(${category.id})">
                                Xóa
                            </button>

                        </td>

                    </tr>
                `;
            });

            $("#categoryTable").html(html);
        }
    });
}

function saveCategory() {

    let category = {

        name: $("#name").val()
    };

    $.ajax({

        url: CATEGORY_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(category),

        success: function () {

            alert("Thêm Category thành công!");

            location.reload();
        }
    });
}

function deleteCategory(id) {

    if (!confirm("Bạn có chắc muốn xóa?")) {
        return;
    }

    $.ajax({

        url: CATEGORY_URL + "/" + id,
        type: "DELETE",

        success: function () {

            alert("Xóa thành công!");

            loadCategories();
        }
    });
}

function editCategory(id) {

    $.ajax({

        url: CATEGORY_URL + "/" + id,
        type: "GET",

        success: function (category) {

            $("#categoryId").val(category.id);
            $("#name").val(category.name);

            let modal = new bootstrap.Modal(
                document.getElementById("categoryModal")
            );

            modal.show();
        }
    });
}
