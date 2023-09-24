  $(document).ready(function() {
      var table = $('#listeProduit').DataTable();


      $('#FiltreCategorie').on('change', function() {
          var selectedCategory = $(this).val();
          table.columns(2).search(selectedCategory).draw();
      });
       $('#FiltreCategorie').change();
  });