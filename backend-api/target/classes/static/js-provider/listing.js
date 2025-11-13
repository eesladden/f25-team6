document.getElementById('searchBar').addEventListener('input', function() {
    const query = this.value.toLowerCase();
    const listings = document.querySelectorAll('.col-md-4');
    listings.forEach(function(listing) {
        const cardName = listing.querySelector('.card-title').textContent.toLowerCase();
        if (cardName.includes(query)) {
            listing.style.display = '';
        } else {
            listing.style.display = 'none';
        }
    });
});