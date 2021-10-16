extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start FieldDeclNode *****
global _java.lang.String_FIELD_chars
_java.lang.String_FIELD_chars:
mov ebp, esp
mov eax, 0 ; default initialization
mov ebx, [__THIS]
mov ebx, [ebx]
add ebx, 4
mov [ebx], eax ; initialize field
mov esp, ebp
ret ; return
 ; return from nonstatic field initializer
; *****END FieldDeclNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_length#
_java.lang.String_METHOD_length#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_2
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_2:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_1:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_charAt#int#
_java.lang.String_METHOD_charAt#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_4
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_4:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_5:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_6 
call __exception ;  nullPointerException
_java.lang.String_noError_6:
cmp ecx, ebx 
jge _java.lang.String_noError_7 
call __exception ; negative array index
_java.lang.String_noError_7:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_8 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_8:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_3:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.String_CONSTRUCTOR_String#
_java.lang.String_CONSTRUCTOR_String#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for String
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; save registers
push esi
push ebx
push ecx
push edx
push ebp

call _java.lang.String_FIELD_chars ; calls nonstatic field initializer
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_9
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_9:
add eax, 4 ; get address of nonstatic field chars
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_11:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_12
call __exception; negative array size
_java.lang.String_noError_12:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_13:
cmp ecx, 0 
je _java.lang.String_endArrayInit_14
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_13
_java.lang.String_endArrayInit_14:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_10:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.String_CONSTRUCTOR_String#char$$#
_java.lang.String_CONSTRUCTOR_String#char$$#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for String
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; save registers
push esi
push ebx
push ecx
push edx
push ebp

call _java.lang.String_FIELD_chars ; calls nonstatic field initializer
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****Start AssignmentNode *****
; *****Start FieldAcccessNode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_15
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_15:
add eax, 4 ; get address of nonstatic field chars
; *****END FieldAcccessNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_17:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_18
call __exception; negative array size
_java.lang.String_noError_18:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_19:
cmp ecx, 0 
je _java.lang.String_endArrayInit_20
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_19
_java.lang.String_endArrayInit_20:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_16:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_23:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.lang.String_ForLoop_21:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_25:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_26:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_27
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_24
_java.lang.String_IfLT_27:
mov eax, 1
_java.lang.String_BinOpEndLabel_24:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_22
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start FieldAcccessNode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_28
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_28:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get the value
; *****END FieldAcccessNode *****
; *****END PrimaryNoNewArrayNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_29:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_30 
call __exception ;  nullPointerException
_java.lang.String_noError_30:
cmp ecx, ebx 
jge _java.lang.String_noError_31 
call __exception ; negative array index
_java.lang.String_noError_31:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_32 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_32:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_34:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_35 
call __exception ;  nullPointerException
_java.lang.String_noError_35:
cmp ecx, ebx 
jge _java.lang.String_noError_36 
call __exception ; negative array index
_java.lang.String_noError_36:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_37 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_37:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_33:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_39:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_40:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_41:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_42:
_java.lang.String_BinOpEndLabel_38:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_21
_java.lang.String_doneFor_22:
; *****END forstmtnode *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.String_CONSTRUCTOR_String#String#
_java.lang.String_CONSTRUCTOR_String#String#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for String
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; save registers
push esi
push ebx
push ecx
push edx
push ebp

call _java.lang.String_FIELD_chars ; calls nonstatic field initializer
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****Start AssignmentNode *****
; *****Start FieldAcccessNode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_43
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_43:
add eax, 4 ; get address of nonstatic field chars
; *****END FieldAcccessNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_45
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_45:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_44:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_concat#String#
_java.lang.String_METHOD_concat#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_46:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_50
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_50:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_49:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_52
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_52:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_51:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_53:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_54:
_java.lang.String_BinOpEndLabel_48:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_55
call __exception; negative array size
_java.lang.String_noError_55:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_56:
cmp ecx, 0 
je _java.lang.String_endArrayInit_57
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_56
_java.lang.String_endArrayInit_57:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_47:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local newchars
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_60:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_58:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_62:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_64
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_64:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_63:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_65
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_61
_java.lang.String_IfLT_65:
mov eax, 1
_java.lang.String_BinOpEndLabel_61:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_59
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local newchars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_66:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_67 
call __exception ;  nullPointerException
_java.lang.String_noError_67:
cmp ecx, ebx 
jge _java.lang.String_noError_68 
call __exception ; negative array index
_java.lang.String_noError_68:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_69 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_69:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_71
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_71:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_72:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_73 
call __exception ;  nullPointerException
_java.lang.String_noError_73:
cmp ecx, ebx 
jge _java.lang.String_noError_74 
call __exception ; negative array index
_java.lang.String_noError_74:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_75 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_75:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_70:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_77:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_78:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_79:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_80:
_java.lang.String_BinOpEndLabel_76:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_58
_java.lang.String_doneFor_59:
; *****END forstmtnode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_83:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_81:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_85:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_87
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_87:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_86:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_88
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_84
_java.lang.String_IfLT_88:
mov eax, 1
_java.lang.String_BinOpEndLabel_84:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_82
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local newchars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_90:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_92
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_92:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_91:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_93:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_94:
_java.lang.String_BinOpEndLabel_89:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_95 
call __exception ;  nullPointerException
_java.lang.String_noError_95:
cmp ecx, ebx 
jge _java.lang.String_noError_96 
call __exception ; negative array index
_java.lang.String_noError_96:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_97 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_97:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s2
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_99
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_99:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_100:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_101 
call __exception ;  nullPointerException
_java.lang.String_noError_101:
cmp ecx, ebx 
jge _java.lang.String_noError_102 
call __exception ; negative array index
_java.lang.String_noError_102:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_103 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_103:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_98:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_105:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_106:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_107:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_108:
_java.lang.String_BinOpEndLabel_104:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_81
_java.lang.String_doneFor_82:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type String *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local newchars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_110:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument chars

mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_111
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_111:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls String
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_109:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#char#
_java.lang.String_METHOD_valueOf#char#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_113:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_114
call __exception; negative array size
_java.lang.String_noError_114:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_115:
cmp ecx, 0 
je _java.lang.String_endArrayInit_116
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_115
_java.lang.String_endArrayInit_116:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_112:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local newchars
; *****END LocalVariableDeclNode *****
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local newchars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_117:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_118 
call __exception ;  nullPointerException
_java.lang.String_noError_118:
cmp ecx, ebx 
jge _java.lang.String_noError_119 
call __exception ; negative array index
_java.lang.String_noError_119:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_120 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_120:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg c
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_121:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type String *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local newchars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_123:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument chars

mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_124
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_124:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls String
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_122:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#int#
_java.lang.String_METHOD_valueOf#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 15
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_126:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_127
call __exception; negative array size
_java.lang.String_noError_127:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_128:
cmp ecx, 0 
je _java.lang.String_endArrayInit_129
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_128
_java.lang.String_endArrayInit_129:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_125:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local ret
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_130:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local j
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_131:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local wasneg
; *****END LocalVariableDeclNode *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_133:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 2147483648
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_134:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.String_IfEQ_135
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_132
_java.lang.String_IfEQ_135:
mov eax, 1
_java.lang.String_BinOpEndLabel_132:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_136
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 11 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 45
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 50
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 49
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 52
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 55
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 52
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 56
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 51
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 54
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 52
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 56
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_139
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_139:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_137:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_136:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_141:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_142:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_143
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_140
_java.lang.String_IfLT_143:
mov eax, 1
_java.lang.String_BinOpEndLabel_140:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_144
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local wasneg
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_145:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_146:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_144:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_148:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_149:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.String_IfEQ_150
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_147
_java.lang.String_IfEQ_150:
mov eax, 1
_java.lang.String_BinOpEndLabel_147:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_151
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_153:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_154 
call __exception ;  nullPointerException
_java.lang.String_noError_154:
cmp ecx, ebx 
jge _java.lang.String_noError_155 
call __exception ; negative array index
_java.lang.String_noError_155:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_156 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_156:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 48
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_157:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_159:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_160:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_161:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_162:
_java.lang.String_BinOpEndLabel_158:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_152
_java.lang.String_ElseStmt_151:
push esi; 
mov esi, esp ; block start
; *****Start WhileStmt node *****
_java.lang.String_WhileLoop_163:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_166:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_167:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jg _java.lang.String_IfGT_168
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_165
_java.lang.String_IfGT_168:
mov eax, 1
_java.lang.String_BinOpEndLabel_165:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneWhile_164
push esi; 
mov esi, esp ; block start
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_170:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 10
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_171:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp ebx, 0
jne _java.lang.String_noDivisionByZero_172 
call __exception ;  division by 0
_java.lang.String_noDivisionByZero_172:
cdq
idiv ebx
mov eax, edx
_java.lang.String_BinOpEndLabel_169:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local d
; *****END LocalVariableDeclNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_174:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 10
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_175:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp ebx, 0
jne _java.lang.String_noDivisionByZero_176 
call __exception ;  division by 0
_java.lang.String_noDivisionByZero_176:
cdq
idiv ebx
_java.lang.String_BinOpEndLabel_173:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_177:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_178 
call __exception ;  nullPointerException
_java.lang.String_noError_178:
cmp ecx, ebx 
jge _java.lang.String_noError_179 
call __exception ; negative array index
_java.lang.String_noError_179:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_180 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_180:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -24 ; get address of local d
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_183:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 48
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_184:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_185:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_186:
_java.lang.String_BinOpEndLabel_182:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
push eax
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_181:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_188:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_189:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_190:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_191:
_java.lang.String_BinOpEndLabel_187:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_WhileLoop_163
_java.lang.String_doneWhile_164:
; *****END WhileStmt node *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_152:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local wasneg
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_192:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_193
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_194:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_195 
call __exception ;  nullPointerException
_java.lang.String_noError_195:
cmp ecx, ebx 
jge _java.lang.String_noError_196 
call __exception ; negative array index
_java.lang.String_noError_196:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_197 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_197:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 45
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_198:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_200:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_201:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_202:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_203:
_java.lang.String_BinOpEndLabel_199:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_193:
; *****END Ifstmt node *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_205:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_206
call __exception; negative array size
_java.lang.String_noError_206:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_207:
cmp ecx, 0 
je _java.lang.String_endArrayInit_208
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_207
_java.lang.String_endArrayInit_208:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_204:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local ret2
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_211:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_209:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_213:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_214:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_215
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_212
_java.lang.String_IfLT_215:
mov eax, 1
_java.lang.String_BinOpEndLabel_212:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_210
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -16 ; get address of local ret2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_216:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_217 
call __exception ;  nullPointerException
_java.lang.String_noError_217:
cmp ecx, ebx 
jge _java.lang.String_noError_218 
call __exception ; negative array index
_java.lang.String_noError_218:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_219 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_219:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_223:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_224:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_222:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_225:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_221:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_226 
call __exception ;  nullPointerException
_java.lang.String_noError_226:
cmp ecx, ebx 
jge _java.lang.String_noError_227 
call __exception ; negative array index
_java.lang.String_noError_227:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_228 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_228:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_220:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_230:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_231:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_232:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_233:
_java.lang.String_BinOpEndLabel_229:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_209
_java.lang.String_doneFor_210:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type String *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -16 ; get address of local ret2
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_235:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument chars

mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_236
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_236:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls String
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_234:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#short#
_java.lang.String_METHOD_valueOf#short#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
push eax
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_238:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
call _java.lang.String_METHOD_valueOf#int# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_237:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#byte#
_java.lang.String_METHOD_valueOf#byte#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
push eax
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_240:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
call _java.lang.String_METHOD_valueOf#int# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_239:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#boolean#
_java.lang.String_METHOD_valueOf#boolean#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_241:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_242
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 4 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 116
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 114
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 117
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 101
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_246
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_246:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_244:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_243
_java.lang.String_ElseStmt_242:
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 5 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 102
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 97
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 108
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 115
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 101
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_249
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_249:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_247:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_243:
; *****END Ifstmt node *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#Object#
_java.lang.String_METHOD_valueOf#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_251:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_252:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.String_IfEQ_253
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_250
_java.lang.String_IfEQ_253:
mov eax, 1
_java.lang.String_BinOpEndLabel_250:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_254
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 4 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 110
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 117
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 108
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 108
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_258
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_258:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_256:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_255
_java.lang.String_ElseStmt_254:
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_260
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_260:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +16] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_259:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_255:
; *****END Ifstmt node *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_valueOf#String#
_java.lang.String_METHOD_valueOf#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_262:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_263:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.String_IfEQ_264
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_261
_java.lang.String_IfEQ_264:
mov eax, 1
_java.lang.String_BinOpEndLabel_261:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_265
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 4 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 110
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 117
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 108
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 108
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_269
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_269:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_267:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_266
_java.lang.String_ElseStmt_265:
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_270:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_266:
; *****END Ifstmt node *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_equals#Object#
_java.lang.String_METHOD_equals#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_272:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_273:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.String_IfEQ_274
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_271
_java.lang.String_IfEQ_274:
mov eax, 1
_java.lang.String_BinOpEndLabel_271:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_275
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_276:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_275:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_279:
; *****END BinOpExprNode *****
cmp eax, 0 ; if object is null, result is false
je _java.lang.String_BinOpEndLabel_278
; type Assignability
cmp eax, 0; is eax NULL?
je _java.lang.String_endTypeCheck_281
mov eax, [eax] ; get address of vtable
add eax, 4
mov eax, [eax] ; get address of subtype column
mov eax, [eax + 140] ; get subtype bit for String
cmp eax, 0
jne _java.lang.String_endTypeCheck_281
call __exception ; not type assignable
_java.lang.String_endTypeCheck_281:
_java.lang.String_BinOpEndLabel_278:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
mov ecx, 1
sub ecx, eax ; ecx <- 1-eax
mov eax, ecx ; eax <- 1-eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_277:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_282
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_283:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_282:
; *****END Ifstmt node *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_286
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_286:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_285:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument a1

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start FieldAcccessNode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg o
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
push eax
; type Assignability
cmp eax, 0; is eax NULL?
je _java.lang.String_endTypeCheck_289
mov eax, [eax] ; get address of vtable
add eax, 4
mov eax, [eax] ; get address of subtype column
mov eax, [eax + 140] ; get subtype bit for String
cmp eax, 0
jne _java.lang.String_endTypeCheck_289
call __exception ; not type assignable
_java.lang.String_endTypeCheck_289:
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_288:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
; *****END PrimaryNoNewArrayNode *****
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_290
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_290:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get the value
; *****END FieldAcccessNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_287:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument a2

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.util.Arrays_METHOD_equals#char$$#char$$#
call _java.util.Arrays_METHOD_equals#char$$#char$$# ; call static method
add esp, 8 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_284:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_substring#int#int#
_java.lang.String_METHOD_substring#int#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_291:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local k
; *****END LocalVariableDeclNode *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_293:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_294:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_295
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_292
_java.lang.String_IfLT_295:
mov eax, 1
_java.lang.String_BinOpEndLabel_292:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_296
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 0 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_299
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_299:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_297:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_296:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_301:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_303
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_303:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_302:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jg _java.lang.String_IfGT_304
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_300
_java.lang.String_IfGT_304:
mov eax, 1
_java.lang.String_BinOpEndLabel_300:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_305
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 0 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_308
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_308:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_306:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_305:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_310:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_311:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_312
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_309
_java.lang.String_IfLT_312:
mov eax, 1
_java.lang.String_BinOpEndLabel_309:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_313
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 0 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_316
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_316:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_314:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_313:
; *****END Ifstmt node *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_319:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_320:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_318:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_321
call __exception; negative array size
_java.lang.String_noError_321:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_322:
cmp ecx, 0 
je _java.lang.String_endArrayInit_323
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_322
_java.lang.String_endArrayInit_323:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_317:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local ret
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_326:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_324:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_328:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_329:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_330
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_327
_java.lang.String_IfLT_330:
mov eax, 1
_java.lang.String_BinOpEndLabel_327:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_325
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_332:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 8 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_333:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_331:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_334 
call __exception ;  nullPointerException
_java.lang.String_noError_334:
cmp ecx, ebx 
jge _java.lang.String_noError_335 
call __exception ; negative array index
_java.lang.String_noError_335:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_336 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_336:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_338:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_339
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_339:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_337:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local k
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_341:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_342:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_343:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_344:
_java.lang.String_BinOpEndLabel_340:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_324
_java.lang.String_doneFor_325:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type String *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_346:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument chars

mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_347
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_347:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls String
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_345:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_trim#
_java.lang.String_METHOD_trim#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_348:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_349:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local j
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_352:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_350:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_355:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_357
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_357:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_356:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_358
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_354
_java.lang.String_IfLT_358:
mov eax, 1
_java.lang.String_BinOpEndLabel_354:
; *****END BinOpExprNode *****
; *****Start AMP AMP LEFT *****
cmp eax, 1
jne _java.lang.String_AMPAMPLeftNotTrue_359
jmp _java.lang.String_AMPAMPLeftTrue_360
_java.lang.String_AMPAMPLeftNotTrue_359:
mov eax,0
jmp _java.lang.String_BinOpEndLabel_353
_java.lang.String_AMPAMPLeftTrue_360:
; *****END AMP AMP LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_363:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_364
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_364:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_362:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 32
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_365:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jle _java.lang.String_IfLE_366
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_361
_java.lang.String_IfLE_366:
mov eax, 1
_java.lang.String_BinOpEndLabel_361:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of AMPAMP *****
cmp ebx, 1
jne _java.lang.String_notEqualLabel_367
_java.lang.String_EqualLabel_368:
mov eax, 1
jmp _java.lang.String_BinOpEndLabel_353
_java.lang.String_notEqualLabel_367:
mov eax, 0
; *****END Right of AMPAMP *****
_java.lang.String_BinOpEndLabel_353:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_351
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_370:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_371:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_372:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_373:
_java.lang.String_BinOpEndLabel_369:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_350
_java.lang.String_doneFor_351:
; *****END forstmtnode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_378
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_378:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_377:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_379:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_376:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_374:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_382:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_383:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_384
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_381
_java.lang.String_IfGE_384:
mov eax, 1
_java.lang.String_BinOpEndLabel_381:
; *****END BinOpExprNode *****
; *****Start AMP AMP LEFT *****
cmp eax, 1
jne _java.lang.String_AMPAMPLeftNotTrue_385
jmp _java.lang.String_AMPAMPLeftTrue_386
_java.lang.String_AMPAMPLeftNotTrue_385:
mov eax,0
jmp _java.lang.String_BinOpEndLabel_380
_java.lang.String_AMPAMPLeftTrue_386:
; *****END AMP AMP LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_389:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_390
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_390:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_388:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 32
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_391:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jle _java.lang.String_IfLE_392
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_387
_java.lang.String_IfLE_392:
mov eax, 1
_java.lang.String_BinOpEndLabel_387:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of AMPAMP *****
cmp ebx, 1
jne _java.lang.String_notEqualLabel_393
_java.lang.String_EqualLabel_394:
mov eax, 1
jmp _java.lang.String_BinOpEndLabel_380
_java.lang.String_notEqualLabel_393:
mov eax, 0
; *****END Right of AMPAMP *****
_java.lang.String_BinOpEndLabel_380:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_375
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_396:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_397:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.String_BinOpEndLabel_395:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_374
_java.lang.String_doneFor_375:
; *****END forstmtnode *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_399:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_400:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jg _java.lang.String_IfGT_401
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_398
_java.lang.String_IfGT_401:
mov eax, 1
_java.lang.String_BinOpEndLabel_398:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_402
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 0 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_406
call __exception ; nullpointerexception (for constructorcall)
_java.lang.String_noError_406:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_404:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_403
_java.lang.String_ElseStmt_402:
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_408:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local j
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_410:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_411:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_412:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_413:
_java.lang.String_BinOpEndLabel_409:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument j

; *****Done computing arguments *****
mov eax, [esp + 12 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_414
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_414:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +72] ; load method 
call eax 
add esp, 8 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_407:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_403:
; *****END Ifstmt node *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_hashCode#
_java.lang.String_METHOD_hashCode#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_415:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local h
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_418:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.lang.String_ForLoop_416:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_420:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_422
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_422:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_421:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_423
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_419
_java.lang.String_IfLT_423:
mov eax, 1
_java.lang.String_BinOpEndLabel_419:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_417
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local h
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 31
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_426:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local h
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_427:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
imul eax, ebx ; *
_java.lang.String_BinOpEndLabel_425:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_429
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_429:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_430:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_431 
call __exception ;  nullPointerException
_java.lang.String_noError_431:
cmp ecx, ebx 
jge _java.lang.String_noError_432 
call __exception ; negative array index
_java.lang.String_noError_432:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_433 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_433:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_428:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_434:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_435:
_java.lang.String_BinOpEndLabel_424:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_437:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_438:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_439:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_440:
_java.lang.String_BinOpEndLabel_436:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_416
_java.lang.String_doneFor_417:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local h
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_441:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_toString#
_java.lang.String_METHOD_toString#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_442:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_compareTo#Object#
_java.lang.String_METHOD_compareTo#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
push eax
; type Assignability
cmp eax, 0; is eax NULL?
je _java.lang.String_endTypeCheck_445
mov eax, [eax] ; get address of vtable
add eax, 4
mov eax, [eax] ; get address of subtype column
mov eax, [eax + 140] ; get subtype bit for String
cmp eax, 0
jne _java.lang.String_endTypeCheck_445
call __exception ; not type assignable
_java.lang.String_endTypeCheck_445:
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_444:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument other

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_446
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_446:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +84] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_443:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_compareTo#String#
_java.lang.String_METHOD_compareTo#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_447:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_448:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local b
; *****END LocalVariableDeclNode *****
; *****Start WhileStmt node *****
_java.lang.String_WhileLoop_449:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_451:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneWhile_450
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_454:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_456
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_456:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_455:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_457
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_453
_java.lang.String_IfGE_457:
mov eax, 1
_java.lang.String_BinOpEndLabel_453:
; *****END BinOpExprNode *****
; *****Start AMP AMP LEFT *****
cmp eax, 1
jne _java.lang.String_AMPAMPLeftNotTrue_458
jmp _java.lang.String_AMPAMPLeftTrue_459
_java.lang.String_AMPAMPLeftNotTrue_458:
mov eax,0
jmp _java.lang.String_BinOpEndLabel_452
_java.lang.String_AMPAMPLeftTrue_459:
; *****END AMP AMP LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_461:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_463
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_463:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_462:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_464
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_460
_java.lang.String_IfGE_464:
mov eax, 1
_java.lang.String_BinOpEndLabel_460:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of AMPAMP *****
cmp ebx, 1
jne _java.lang.String_notEqualLabel_465
_java.lang.String_EqualLabel_466:
mov eax, 1
jmp _java.lang.String_BinOpEndLabel_452
_java.lang.String_notEqualLabel_465:
mov eax, 0
; *****END Right of AMPAMP *****
_java.lang.String_BinOpEndLabel_452:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_467
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_468:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_467:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_470:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_472
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_472:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_471:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_473
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_469
_java.lang.String_IfGE_473:
mov eax, 1
_java.lang.String_BinOpEndLabel_469:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_474
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_475:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_474:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_477:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_479
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_479:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_478:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_480
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_476
_java.lang.String_IfGE_480:
mov eax, 1
_java.lang.String_BinOpEndLabel_476:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_481
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_482:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_481:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_485
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_485:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_486:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_487 
call __exception ;  nullPointerException
_java.lang.String_noError_487:
cmp ecx, ebx 
jge _java.lang.String_noError_488 
call __exception ; negative array index
_java.lang.String_noError_488:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_489 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_489:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_484:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_491
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_491:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_492:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_493 
call __exception ;  nullPointerException
_java.lang.String_noError_493:
cmp ecx, ebx 
jge _java.lang.String_noError_494 
call __exception ; negative array index
_java.lang.String_noError_494:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_495 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_495:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_490:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_496
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_483
_java.lang.String_IfLT_496:
mov eax, 1
_java.lang.String_BinOpEndLabel_483:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_497
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_498:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_497:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_501
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_501:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_502:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_503 
call __exception ;  nullPointerException
_java.lang.String_noError_503:
cmp ecx, ebx 
jge _java.lang.String_noError_504 
call __exception ; negative array index
_java.lang.String_noError_504:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_505 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_505:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_500:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_507
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_507:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_508:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_509 
call __exception ;  nullPointerException
_java.lang.String_noError_509:
cmp ecx, ebx 
jge _java.lang.String_noError_510 
call __exception ; negative array index
_java.lang.String_noError_510:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_511 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_511:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_506:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jg _java.lang.String_IfGT_512
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_499
_java.lang.String_IfGT_512:
mov eax, 1
_java.lang.String_BinOpEndLabel_499:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_513
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_514:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_513:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_516:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_517:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_518:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_519:
_java.lang.String_BinOpEndLabel_515:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_WhileLoop_449
_java.lang.String_doneWhile_450:
; *****END WhileStmt node *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_520:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_toCharArray#
_java.lang.String_METHOD_toCharArray#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Array Creation *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_523
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_523:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_522:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0 
cmp eax, ebx 
jge _java.lang.String_noError_524
call __exception; negative array size
_java.lang.String_noError_524:
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
_java.lang.String_arrayInitLoop_525:
cmp ecx, 0 
je _java.lang.String_endArrayInit_526
mov ebx, 0
mov [eax], ebx ; initialize to 0
add eax, 4 
sub ecx, 1
jmp _java.lang.String_arrayInitLoop_525
_java.lang.String_endArrayInit_526:
pop eax ; return array location
; *****END Array Creation *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_521:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local ret
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_529:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.lang.String_ForLoop_527:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_531:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax]; load value of LHS
mov eax, [eax+4]; load length of array
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_532:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_533
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_530
_java.lang.String_IfLT_533:
mov eax, 1
_java.lang.String_BinOpEndLabel_530:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_528
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_534:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_535 
call __exception ;  nullPointerException
_java.lang.String_noError_535:
cmp ecx, ebx 
jge _java.lang.String_noError_536 
call __exception ; negative array index
_java.lang.String_noError_536:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_537 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_537:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
; *****END Array Access node *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_539
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_539:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_540:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_541 
call __exception ;  nullPointerException
_java.lang.String_noError_541:
cmp ecx, ebx 
jge _java.lang.String_noError_542 
call __exception ; negative array index
_java.lang.String_noError_542:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_543 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_543:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_538:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_545:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_546:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_547:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_548:
_java.lang.String_BinOpEndLabel_544:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_527
_java.lang.String_doneFor_528:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_549:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.String_METHOD_indexOf#String#
_java.lang.String_METHOD_indexOf#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_550:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local offset
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_551:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_554:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_552:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_556:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_558
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_558:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_557:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_559
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_555
_java.lang.String_IfLT_559:
mov eax, 1
_java.lang.String_BinOpEndLabel_555:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_553
push esi; 
mov esi, esp ; block start
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_560:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local found
; *****END LocalVariableDeclNode *****
; *****Start forstmtnode *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_563:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
_java.lang.String_ForLoop_561:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_565:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg needle
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_567
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_567:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_566:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.String_IfLT_568
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_564
_java.lang.String_IfLT_568:
mov eax, 1
_java.lang.String_BinOpEndLabel_564:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_doneFor_562
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_571:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_572:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_573:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_574:
_java.lang.String_BinOpEndLabel_570:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.String_noError_576
call __exception ; nullpointerexception (for methodcall)
_java.lang.String_noError_576:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_575:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.String_IfGE_577
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_569
_java.lang.String_IfGE_577:
mov eax, 1
_java.lang.String_BinOpEndLabel_569:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_ElseStmt_578
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -16 ; get address of local found
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_580:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_IfStmtEnd_579
_java.lang.String_ElseStmt_578:
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_583
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_583:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_585:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_586:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_587:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_588:
_java.lang.String_BinOpEndLabel_584:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_589 
call __exception ;  nullPointerException
_java.lang.String_noError_589:
cmp ecx, ebx 
jge _java.lang.String_noError_590 
call __exception ; negative array index
_java.lang.String_noError_590:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_591 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_591:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_582:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Array Access node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg needle
mov eax, [eax]; load value of LHS
mov ebx, 0
cmp eax, ebx
jne _java.lang.String_noError_593
call __exception ; nullpointerexception (field access)
_java.lang.String_noError_593:
add eax, 4 ; get address of nonstatic field chars
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
push eax ; store array reference
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_594:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ecx, eax ; store array expression
add eax, 2
shl eax, 2 
mov edx, eax ; store 4 * (array expression+2)
pop eax ; get array reference
push eax
mov ebx, 0
cmp eax, ebx 
jne _java.lang.String_noError_595 
call __exception ;  nullPointerException
_java.lang.String_noError_595:
cmp ecx, ebx 
jge _java.lang.String_noError_596 
call __exception ; negative array index
_java.lang.String_noError_596:
mov ebx, [eax+4] ; get array length
cmp ecx, ebx 
jl _java.lang.String_noError_597 
call __exception ; ArrayIndexOutOfBoundsException
_java.lang.String_noError_597:
add eax, edx ; put address of array element in eax
pop edx; put address of array in edx
mov eax, [eax]
; *****END Array Access node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_592:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne _java.lang.String_IfNEQ_598
mov eax, 0
jmp _java.lang.String_BinOpEndLabel_581
_java.lang.String_IfNEQ_598:
mov eax, 1
_java.lang.String_BinOpEndLabel_581:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_599
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -16 ; get address of local found
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_600:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_599:
; *****END Ifstmt node *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfStmtEnd_579:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_602:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_603:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_604:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_605:
_java.lang.String_BinOpEndLabel_601:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_561
_java.lang.String_doneFor_562:
; *****END forstmtnode *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -16 ; get address of local found
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_606:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.String_IfThenStmt_607
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_608:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
_java.lang.String_IfThenStmt_607:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local offset
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_610:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_611:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.String_beginNonStringAdd_612:
add eax, ebx ; +
_java.lang.String_endNonStringAdd_613:
_java.lang.String_BinOpEndLabel_609:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.String_ForLoop_552
_java.lang.String_doneFor_553:
; *****END forstmtnode *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.String_BinOpEndLabel_614:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
