 public String generateToken(UserDetails userDetails, User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getRole());
    claims.put("userId", user.getId());

    return createToken(claims, userDetails.getUsername());
}
