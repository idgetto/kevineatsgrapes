<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Kevin Eats Grapes</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- FONT
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link href="http://fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet"
        type="text/css">

  <!-- CSS
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="stylesheet" href="assets/css/normalize.css">
  <link rel="stylesheet" href="assets/css/skeleton.css">
  <link rel="stylesheet" href="assets/css/custom.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="assets/images/favicon.png">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="assets/js/countUp.js"></script>

  <script>
    $(document).ready(function() {
      $.get(getUrl(), function(data, status){
        if (status === "success") {
          addTotal(data['total']);
          addTable(data['meals']);
        }
      });
    });

    function getUrl() {
      return $(location).attr("protocol") + "//" + $(location).attr("host") + "/meals";
    }

    function addTotal(total) {
      $("#total").append("<h1 class=\"jumbo\" id=\"total-grapes\">" + total + "</h1>");
      $("#total").append("<h2> grapes</h2>");
      var numAnim = new CountUp("total-grapes", 0, total, 0, 2.5);
      numAnim.start();
    }

    function addTable(meals) {
      meals.forEach(function(meal) {
        tr = "<tr><td>" + meal['date'] + "</td><td>" + meal['count'] + "</td></tr>";
        $("#meal-table").append(tr);
      });
    }
  </script>

</head>
<body>

<!-- Primary Page Layout
–––––––––––––––––––––––––––––––––––––––––––––––––– -->
<div class="container">
  <div class="row">
    <div class="twelve columns">
      <div class="hero">
        <h2><a class="sly" target="_blank" href="https://www.linkedin.com/in/kevin-crispie-94374418/">Kevin</a> has eaten</h2>
        <div id="total">
        </div>
        <table class="u-full-width" style="margin-top: 6em">
          <thead>
          <tr>
            <th>Date</th>
            <th># Grapes</th>
          </tr>
          </thead>
          <tbody id="meal-table">
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
