const allDramas = [];
const allMovies = [];

fetch("http://localhost:8080/dramas/drama-api").then(response => response.json()).then(data => {
    for (let drama of data) {
        allDramas.push(drama);
    }
})
fetch("http://localhost:8080/movies/movie-api").then(response => response.json()).then(data => {
    for (let movie of data) {
        allMovies.push(movie);
    }
})

console.log(allDramas)
console.log(allMovies)

window.onload = (event) => {
    console.log('page is fully loaded');
    const searchButton = document.getElementById('search-button');
    const searchInput = document.getElementById('search-input');


    let toList = document.getElementById('listSearch');

    searchButton.addEventListener('click', () => {
        const inputValue = searchInput.value.toLowerCase();
        let filteredDramas = allDramas.filter(drama => drama.title.toLowerCase().includes(inputValue));
        let filteredMovies = allMovies.filter(movie => movie.title.toLowerCase().includes(inputValue));

        let all = [];
        if (filteredDramas.length >= 1) {
            all.push(filteredDramas);
        }

        let all2 = [];
        if (filteredMovies.length >= 1) {
            all2.push(filteredMovies);
        }


        displayFoundDramas(all);
        displayFoundMovies(all2);
    });


    const displayFoundDramas = (entity) => {
        entity.forEach(entity => {
            toList.innerHTML = entity
                .map((d) => {
                    console.log(d.imageURL)
                    return `<div class="col-md-3">
                    <div class=" card mb-4 box-shadow">
                          <img src="${d.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 300px; width: 100%; display: block;">
                        <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom">Title: ${d.title}</p>
                        <p class="card-text border-bottom">Country: ${d.country.name}</p>
                        <p class="card-text border-bottom">Episodes: ${d.episodes}</p>
                        <p class="card-text border-bottom">Release Date: ${d.releaseDate}</p>
                        <p class="card-text border-bottom">Director: ${d.director}</p>
                    </div>
                    <br>
                    <div class="d-flex justify-content-between align-items-center text-dark">
                        <div class="btn-group">
                            <a href="/dramas/drama-details/${d.id}" type="button" class="btn btn-sm btn-info">Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="/dramas/delete/${d.id}" type="button" class="btn btn-sm btn-info">Delete</a>
                        </div>
                    </div>
                </div>
                    </div>
                </div>`
                }).join('');
        })





    }

    const displayFoundMovies = (movie) => {
        console.log("this is movies")
        console.log(movie)
        movie.forEach(movie => {
            toList.innerHTML = movie
                .map((m) => {
                    console.log(m.imageURL)
                    return `<div class="col-md-3">
                    <div class=" card mb-4 box-shadow">
                          <img src="${m.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 300px; width: 100%; display: block;">
                        <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom">Title: ${m.title}</p>
                        <p class="card-text border-bottom">Country: ${m.country.name}</p>
                        <p class="card-text border-bottom">Release Date: ${m.releaseDate}</p>
                        <p class="card-text border-bottom">Director: ${m.director}</p>
                    </div>
                    <br>
                    <div class="d-flex justify-content-between align-items-center text-dark">
                        <div class="btn-group">
                            <a href="/movies/movie-details/${m.id}" type="button" class="btn btn-sm btn-info">Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="/movies/delete/${m.id}" type="button" class="btn btn-sm btn-info">Delete</a>
                        </div>
                    </div>
                </div>
                    </div>
                </div>`
                }).join('');
        })





    }

};
