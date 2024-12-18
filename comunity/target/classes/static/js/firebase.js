const firebaseConfig = {
  apiKey: "AIzaSyBdr1vbQ4vnU-Y-s0CkobcJYBjphONyY5Y",
  authDomain: "mydata-53d11.firebaseapp.com",
  projectId: "mydata-53d11",
  storageBucket: "mydata-53d11.firebasestorage.app",
  messagingSenderId: "637448803851",
  appId: "1:637448803851:web:3e6f45541781656747190e"
}

firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();