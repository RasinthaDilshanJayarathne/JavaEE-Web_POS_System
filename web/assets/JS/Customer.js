$("#customer").click(function (){
    loadAllCustomer();
})

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

$("#btnCustomerDelete").click(function (){
    let custId = $("#txtCustId").val();
    $.ajax({
        url:"customer?CustId="+custId,
        method:"DELETE",
        //data :data,

        success : function (resp){
            console.log(resp);
            loadAllCustomer();
        }
    })

});

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

                var row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td></tr>`;
                $("#customerTable").append(row);
            }
            bindClickEvent();
        }
    });
}

function bindClickEvent(){
    $("#customerTable>tr").click(function (){
        let id=$(this).children().eq(0).text();
        let name=$(this).children().eq(1).text();
        let address=$(this).children().eq(2).text();
        let contact=$(this).children().eq(3).text();

        $("#txtCustId").val(id);
        $("#txtCustName").val(name);
        $("#txtCustAddress").val(address);
        $("#txtCustPhoneNo").val(contact);

    });
}