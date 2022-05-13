$("#customer").click(function (){
    loadAllCustomer();
})

function loadAllCustomer() {
    $("#customerTable").empty();
    $.ajax({
        url: "customer",
        method: "GET",
        /* dataType :"json",*/
        success: function (resp) {
            for (var i in resp) {
                var id = resp[i].id;
                var name = resp[i].name;
                var address = resp[i].address;
                var contact = resp[i].contact;

                var row = `<tr>
                                <td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td>
                                <td><button id="btnCustomerDelete" type="button" class="btn-sm btn-danger">Delete</button></td>
                                </tr>`;
                $("#customerTable").append(row);
            }
        }
    });
}

$("#popCustBtnAdd").click(function () {
    let data = $("#customerForm").serialize();
    console.log(data)
    $.ajax({
        url: "customer",
        method: "POST",
        data: data,
        success: function (resp) {
            alert(resp)
            loadAllCustomer();
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
            console.log(ob.responseText);
        }
    });
});