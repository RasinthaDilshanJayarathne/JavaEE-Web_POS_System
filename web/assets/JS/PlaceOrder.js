$("#placeOrder").click(function (){
    loadCustomerComboData();
});

function loadCustomerComboData(){
    $("#custChombo").empty();
    $("#custChombo").append($("<option></option>").attr("value", 0).text("Select Ids"));
    var count=1;
   $.ajax({
       url:"customer?option=GETAll",
       method:"GET",
       success:function (res){
           for (let ids of res.data){
               $("#custChombo").append($("<option></option>").attr("value", count).text(ids.id));
                count++;
           }
       },
       error:function (ob, textStatus, error) {
           alert(textStatus);
       }
   })
}

$("#custChombo").click(function () {
    console.log("End");

    var cusId = $("#custChombo option:selected").text();
    var address = $("#orderAddress").val();
    var contact = $("#orderTelephoneNo").val();
    var name = $("#orderCustName").val();

    $.ajax({
        url: "customer?option=GETAll",
        method: "GET",
        success:function (res){
            for (const customer of res.data){
                if(customer.id==cusId){

                    name=customer.name;
                    address=customer.address;
                    contact=customer.contact;

                    $("#orderAddress").val(address);
                    $("#orderTelephoneNo").val(contact);
                    $("#orderCustName").val(name);
                }
            }
        }
    })
});
