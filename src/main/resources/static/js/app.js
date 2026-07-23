document.addEventListener('DOMContentLoaded', () => {
    let currentWeatherData = null;
    let isFahrenheit = false;
    let currentUser = null;
    let jwtToken = localStorage.getItem('weather_app_token');

    // DOM Elements
    const cityInput = document.getElementById('cityInput');
    const searchBtn = document.getElementById('searchBtn');
    const unitToggle = document.getElementById('unitToggle');
    const unitC = document.getElementById('unitC');
    const unitF = document.getElementById('unitF');

    const cityName = document.getElementById('cityName');
    const countryCode = document.getElementById('countryCode');
    const currentTemp = document.getElementById('currentTemp');
    const weatherIconContainer = document.getElementById('weatherIconContainer');
    const weatherDesc = document.getElementById('weatherDesc');
    const tempMax = document.getElementById('tempMax');
    const tempMin = document.getElementById('tempMin');
    const feelsLike = document.getElementById('feelsLike');

    const windSpeed = document.getElementById('windSpeed');
    const humidity = document.getElementById('humidity');
    const pressure = document.getElementById('pressure');
    const uvIndex = document.getElementById('uvIndex');
    const aqi = document.getElementById('aqi');
    const visibility = document.getElementById('visibility');
    const forecastContainer = document.getElementById('forecastContainer');

    const favoriteBtn = document.getElementById('favoriteBtn');
    const favHeartIcon = document.getElementById('favHeartIcon');
    const favoritesChips = document.getElementById('favoritesChips');

    const authModal = document.getElementById('authModal');
    const openAuthModalBtn = document.getElementById('openAuthModalBtn');
    const closeAuthModalBtn = document.getElementById('closeAuthModalBtn');
    const tabLoginBtn = document.getElementById('tabLoginBtn');
    const tabRegisterBtn = document.getElementById('tabRegisterBtn');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const authBox = document.getElementById('authBox');

    // Initialize App
    init();

    function init() {
        checkAuth();
        fetchWeather('London');
        setupEventListeners();
    }

    function setupEventListeners() {
        searchBtn.addEventListener('click', () => {
            if (cityInput.value.trim()) {
                fetchWeather(cityInput.value.trim());
            }
        });

        cityInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter' && cityInput.value.trim()) {
                fetchWeather(cityInput.value.trim());
            }
        });

        unitToggle.addEventListener('click', () => {
            isFahrenheit = !isFahrenheit;
            unitC.classList.toggle('active', !isFahrenheit);
            unitF.classList.toggle('active', isFahrenheit);
            if (currentWeatherData) renderWeather(currentWeatherData);
        });

        openAuthModalBtn.addEventListener('click', () => authModal.classList.remove('hidden'));
        closeAuthModalBtn.addEventListener('click', () => authModal.classList.add('hidden'));

        tabLoginBtn.addEventListener('click', () => {
            tabLoginBtn.classList.add('active');
            tabRegisterBtn.classList.remove('active');
            loginForm.classList.remove('hidden');
            registerForm.classList.add('hidden');
        });

        tabRegisterBtn.addEventListener('click', () => {
            tabRegisterBtn.classList.add('active');
            tabLoginBtn.classList.remove('active');
            registerForm.classList.remove('hidden');
            loginForm.classList.add('hidden');
        });

        loginForm.addEventListener('submit', handleLogin);
        registerForm.addEventListener('submit', handleRegister);

        favoriteBtn.addEventListener('click', toggleFavorite);
    }

    async function fetchWeather(city) {
        try {
            const response = await fetch(`/api/weather/current?city=${encodeURIComponent(city)}`);
            if (!response.ok) throw new Error('City not found');
            const data = await response.json();
            currentWeatherData = data;
            renderWeather(data);
            checkIfFavorite(data.city);
        } catch (err) {
            console.error('Weather error:', err);
        }
    }

    function renderWeather(data) {
        // Theme transition
        const condition = data.condition ? data.condition.toLowerCase() : 'clear';
        document.body.className = '';
        if (condition.includes('clear')) document.body.classList.add('weather-clear');
        else if (condition.includes('cloud')) document.body.classList.add('weather-clouds');
        else if (condition.includes('rain')) document.body.classList.add('weather-rain');
        else if (condition.includes('snow')) document.body.classList.add('weather-snow');
        else if (condition.includes('thunder')) document.body.classList.add('weather-thunderstorm');
        else document.body.classList.add('weather-clear');

        cityName.textContent = data.city;
        countryCode.textContent = data.country || 'GLOBAL';

        const tempVal = isFahrenheit ? (data.temp * 9/5 + 32) : data.temp;
        currentTemp.textContent = Math.round(tempVal);

        const iconClass = getFontAwesomeIcon(data.condition);
        weatherIconContainer.innerHTML = `<i class="${iconClass}"></i>`;
        weatherDesc.textContent = data.description || data.condition;

        const maxV = isFahrenheit ? (data.tempMax * 9/5 + 32) : data.tempMax;
        const minV = isFahrenheit ? (data.tempMin * 9/5 + 32) : data.tempMin;
        const feelsV = isFahrenheit ? (data.feelsLike * 9/5 + 32) : data.feelsLike;
        const unitSym = isFahrenheit ? '°F' : '°C';

        tempMax.textContent = `${Math.round(maxV)}${unitSym}`;
        tempMin.textContent = `${Math.round(minV)}${unitSym}`;
        feelsLike.textContent = `${Math.round(feelsV)}${unitSym}`;

        windSpeed.innerHTML = `${data.windSpeed} <small>km/h</small>`;
        humidity.innerHTML = `${data.humidity}<small>%</small>`;
        pressure.innerHTML = `${data.pressure} <small>hPa</small>`;
        uvIndex.innerHTML = `${data.uvIndex} <small class="uv-level">${getUvText(data.uvIndex)}</small>`;
        aqi.innerHTML = `${getAqiText(data.airQualityIndex)} <small class="aqi-badge">AQI ${data.airQualityIndex}</small>`;
        visibility.innerHTML = `${(data.visibility / 1000).toFixed(1)} <small>km</small>`;

        renderForecast(data.forecast || []);
    }

    function renderForecast(forecast) {
        forecastContainer.innerHTML = '';
        forecast.forEach(day => {
            const minV = isFahrenheit ? (day.tempMin * 9/5 + 32) : day.tempMin;
            const maxV = isFahrenheit ? (day.tempMax * 9/5 + 32) : day.tempMax;
            const unitSym = isFahrenheit ? '°F' : '°C';
            const iconClass = getFontAwesomeIcon(day.condition);

            const card = document.createElement('div');
            card.className = 'forecast-card';
            card.innerHTML = `
                <div class="forecast-day">${day.dayOfWeek}</div>
                <div class="forecast-icon"><i class="${iconClass}"></i></div>
                <div class="forecast-temps">
                    <span>${Math.round(maxV)}${unitSym}</span>
                    <span class="min-t">${Math.round(minV)}${unitSym}</span>
                </div>
            `;
            forecastContainer.appendChild(card);
        });
    }

    function getFontAwesomeIcon(condition) {
        if (!condition) return 'fa-solid fa-sun';
        const cond = condition.toLowerCase();
        if (cond.includes('clear')) return 'fa-solid fa-sun icon-sun';
        if (cond.includes('cloud')) return 'fa-solid fa-cloud';
        if (cond.includes('rain')) return 'fa-solid fa-cloud-showers-heavy';
        if (cond.includes('snow')) return 'fa-solid fa-snowflake';
        if (cond.includes('thunder')) return 'fa-solid fa-bolt-lightning';
        return 'fa-solid fa-cloud-sun';
    }

    function getUvText(uv) {
        if (uv <= 2) return 'Low';
        if (uv <= 5) return 'Moderate';
        if (uv <= 7) return 'High';
        return 'Very High';
    }

    function getAqiText(aqiVal) {
        if (aqiVal === 1) return 'Good';
        if (aqiVal === 2) return 'Fair';
        if (aqiVal === 3) return 'Moderate';
        return 'Poor';
    }

    // Auth & JWT Handling
    async function checkAuth() {
        if (!jwtToken) {
            renderUnauthenticatedUI();
            return;
        }

        try {
            const res = await fetch('/api/auth/me', {
                headers: { 'Authorization': `Bearer ${jwtToken}` }
            });
            if (res.ok) {
                currentUser = await res.json();
                renderAuthenticatedUI();
                fetchFavorites();
            } else {
                logout();
            }
        } catch (e) {
            console.error('Auth verification error:', e);
            logout();
        }
    }

    function renderAuthenticatedUI() {
        authBox.innerHTML = `
            <div style="display:flex; align-items:center; gap:10px;">
                <span style="font-size:0.9rem; font-weight:600; color:var(--accent-color);">
                    <i class="fa-solid fa-user-check"></i> ${currentUser.username}
                </span>
                <button id="logoutBtn" class="nav-btn" style="color:#ef4444;"><i class="fa-solid fa-right-from-bracket"></i></button>
            </div>
        `;
        document.getElementById('logoutBtn').addEventListener('click', logout);
    }

    function renderUnauthenticatedUI() {
        authBox.innerHTML = `
            <button class="btn-primary" id="openAuthModalBtn">
                <i class="fa-solid fa-user-lock"></i> Login / Register
            </button>
        `;
        document.getElementById('openAuthModalBtn').addEventListener('click', () => authModal.classList.remove('hidden'));
        favoritesChips.innerHTML = `<span class="chip-placeholder">Login to save your favorite cities</span>`;
    }

    function logout() {
        jwtToken = null;
        currentUser = null;
        localStorage.removeItem('weather_app_token');
        renderUnauthenticatedUI();
        favHeartIcon.className = 'fa-regular fa-heart';
    }

    async function handleLogin(e) {
        e.preventDefault();
        const username = document.getElementById('loginUsername').value;
        const password = document.getElementById('loginPassword').value;
        const errDiv = document.getElementById('loginError');

        try {
            const res = await fetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            const data = await res.json();
            if (res.ok) {
                jwtToken = data.token;
                localStorage.setItem('weather_app_token', jwtToken);
                authModal.classList.add('hidden');
                checkAuth();
            } else {
                errDiv.textContent = data.message || 'Login failed';
                errDiv.classList.remove('hidden');
            }
        } catch (err) {
            errDiv.textContent = 'Server error during login';
            errDiv.classList.remove('hidden');
        }
    }

    async function handleRegister(e) {
        e.preventDefault();
        const username = document.getElementById('regUsername').value;
        const email = document.getElementById('regEmail').value;
        const password = document.getElementById('regPassword').value;
        const errDiv = document.getElementById('registerError');

        try {
            const res = await fetch('/api/auth/signup', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, email, password })
            });

            const data = await res.json();
            if (res.ok) {
                jwtToken = data.token;
                localStorage.setItem('weather_app_token', jwtToken);
                authModal.classList.add('hidden');
                checkAuth();
            } else {
                errDiv.textContent = data.message || 'Registration failed';
                errDiv.classList.remove('hidden');
            }
        } catch (err) {
            errDiv.textContent = 'Server error during registration';
            errDiv.classList.remove('hidden');
        }
    }

    // Favorites Logic
    async function fetchFavorites() {
        if (!jwtToken) return;
        try {
            const res = await fetch('/api/favorites', {
                headers: { 'Authorization': `Bearer ${jwtToken}` }
            });
            if (res.ok) {
                const favs = await res.json();
                renderFavorites(favs);
                if (currentWeatherData) checkIfFavorite(currentWeatherData.city);
            }
        } catch (e) {
            console.error('Fetch favorites error:', e);
        }
    }

    function renderFavorites(favs) {
        if (!favs || favs.length === 0) {
            favoritesChips.innerHTML = `<span class="chip-placeholder">No favorite cities saved yet.</span>`;
            return;
        }

        favoritesChips.innerHTML = '';
        favs.forEach(fav => {
            const chip = document.createElement('div');
            chip.className = 'fav-chip';
            chip.innerHTML = `
                <span><i class="fa-solid fa-location-dot"></i> ${fav.cityName}</span>
                <i class="fa-solid fa-xmark del-chip" data-id="${fav.id}"></i>
            `;
            chip.querySelector('span').addEventListener('click', () => fetchWeather(fav.cityName));
            chip.querySelector('.del-chip').addEventListener('click', (e) => {
                e.stopPropagation();
                deleteFavorite(fav.id);
            });
            favoritesChips.appendChild(chip);
        });
    }

    async function toggleFavorite() {
        if (!currentUser) {
            authModal.classList.remove('hidden');
            return;
        }

        if (!currentWeatherData) return;

        const isFav = favHeartIcon.classList.contains('fa-solid');
        if (isFav) {
            // Delete favorite
            const res = await fetch('/api/favorites', {
                headers: { 'Authorization': `Bearer ${jwtToken}` }
            });
            if (res.ok) {
                const list = await res.json();
                const item = list.find(f => f.cityName.toLowerCase() === currentWeatherData.city.toLowerCase());
                if (item) await deleteFavorite(item.id);
            }
        } else {
            // Add favorite
            await fetch('/api/favorites', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${jwtToken}`
                },
                body: JSON.stringify({
                    cityName: currentWeatherData.city,
                    country: currentWeatherData.country,
                    latitude: currentWeatherData.lat,
                    longitude: currentWeatherData.lon
                })
            });
            fetchFavorites();
            favHeartIcon.className = 'fa-solid fa-heart';
        }
    }

    async function deleteFavorite(id) {
        if (!jwtToken) return;
        await fetch(`/api/favorites/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': `Bearer ${jwtToken}` }
        });
        fetchFavorites();
        if (currentWeatherData) checkIfFavorite(currentWeatherData.city);
    }

    async function checkIfFavorite(cityName) {
        if (!jwtToken) {
            favHeartIcon.className = 'fa-regular fa-heart';
            return;
        }
        try {
            const res = await fetch('/api/favorites', {
                headers: { 'Authorization': `Bearer ${jwtToken}` }
            });
            if (res.ok) {
                const favs = await res.json();
                const exists = favs.some(f => f.cityName.toLowerCase() === cityName.toLowerCase());
                favHeartIcon.className = exists ? 'fa-solid fa-heart' : 'fa-regular fa-heart';
            }
        } catch (e) {
            console.error(e);
        }
    }
});
